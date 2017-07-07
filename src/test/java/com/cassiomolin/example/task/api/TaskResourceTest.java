package com.cassiomolin.example.task.api;

import com.cassiomolin.example.task.api.model.CreateTaskDetails;
import com.cassiomolin.example.task.api.model.QueryTaskResult;
import com.cassiomolin.example.task.api.model.UpdateTaskDetails;
import com.cassiomolin.example.task.api.model.UpdateTaskStatusDetails;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Tests for the task resource class.
 *
 * @author cassiomolin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskResourceTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/api";
    }

    @Test
    public void createTask() {

        CreateTaskDetails createTaskDetails = new CreateTaskDetails();
        createTaskDetails.setDescription("Pay electricity bill");

        given()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(createTaskDetails)
        .expect()
            .statusCode(Status.CREATED.getStatusCode())
            .header(HttpHeaders.LOCATION, notNullValue())
        .when()
            .post("/tasks")
        .then()
            .log().all();
    }

    @Test
    public void createTaskWithInvalidDetails() {

        CreateTaskDetails createTaskDetails = new CreateTaskDetails();

        given()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(createTaskDetails)
        .expect()
            .statusCode(Status.BAD_REQUEST.getStatusCode())
        .when()
            .post("/tasks")
        .then()
            .log().all();
    }

    @Test
    public void findTasks() {

        QueryTaskResult[] taskQueryResults =

            given()
                .accept(MediaType.APPLICATION_JSON)
            .expect()
                .statusCode(Status.OK.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
            .when()
                .get("/tasks")
            .then()
                .log().all()
                .extract()
                    .body().as(QueryTaskResult[].class);

        Arrays.stream(taskQueryResults).forEach(task -> {
            assertNotNull(task.getId());
            assertNotNull(task.getDescription());
            assertNotNull(task.getCompleted());
        });
    }

    @Test
    public void getTask() {

        QueryTaskResult queryTaskResult =

            given()
                .accept(MediaType.APPLICATION_JSON)
                .pathParam("taskId", 1)
            .expect()
                .statusCode(Status.OK.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
            .when()
                .get("/tasks/{taskId}")
            .then()
                .log().all()
                .extract()
                    .body().as(QueryTaskResult.class);

        assertNotNull(queryTaskResult.getId());
        assertNotNull(queryTaskResult.getDescription());
        assertNotNull(queryTaskResult.getCompleted());
    }

    @Test
    public void getTaskWithInvalidIdentifier() {

        given()
            .accept(MediaType.APPLICATION_JSON)
            .pathParam("taskId", Integer.MAX_VALUE)
        .expect()
            .statusCode(Status.NOT_FOUND.getStatusCode())
            .contentType(MediaType.APPLICATION_JSON)
        .when()
            .get("/tasks/{taskId}")
        .then()
            .log().all();
    }

    @Test
    public void updateTask() {

        UpdateTaskDetails updateTaskDetails = new UpdateTaskDetails();
        updateTaskDetails.setDescription("Buy chocolate");
        updateTaskDetails.setCompleted(false);

        given()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(updateTaskDetails)
            .pathParam("taskId", 1)
        .expect()
            .statusCode(Status.NO_CONTENT.getStatusCode())
        .when()
            .put("/tasks/{taskId}")
        .then()
            .log().all();
    }

    @Test
    public void updateTaskWithInvalidDetails() {

        UpdateTaskDetails updateTaskDetails = new UpdateTaskDetails();

        given()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(updateTaskDetails)
            .pathParam("taskId", 1)
        .expect()
            .statusCode(Status.BAD_REQUEST.getStatusCode())
        .when()
            .put("/tasks/{taskId}")
        .then()
            .log().all();
    }

    @Test
    public void deleteTask() {

        given()
            .pathParam("taskId", 1)
        .expect()
            .statusCode(Status.NO_CONTENT.getStatusCode())
        .when()
            .delete("/tasks/{taskId}")
        .then()
            .log().all();
    }

    @Test
    public void deleteAllCompletedTasks() {

        given()
            .queryParam("completed", true)
        .expect()
            .statusCode(Status.NO_CONTENT.getStatusCode())
        .when()
            .delete("/tasks")
        .then()
            .log().all();
    }


    @Test
    public void deleteTaskWithInvalidIdentifier() {

        given()
            .pathParam("taskId", Integer.MAX_VALUE)
        .expect()
            .statusCode(Status.NOT_FOUND.getStatusCode())
        .when()
            .delete("/tasks/{taskId}")
        .then()
            .log().all();
    }

    @Test
    public void updateTaskStatus() {

        UpdateTaskStatusDetails updateTaskStatusDetails = new UpdateTaskStatusDetails();
        updateTaskStatusDetails.setValue(true);

        given()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(updateTaskStatusDetails)
            .pathParam("taskId", 1)
        .expect()
            .statusCode(Status.NO_CONTENT.getStatusCode())
        .when()
            .put("/tasks/{taskId}/completed")
        .then()
            .log().all();
    }
}
