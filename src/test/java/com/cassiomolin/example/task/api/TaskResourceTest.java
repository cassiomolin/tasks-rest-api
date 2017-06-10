package com.cassiomolin.example.task.api;

import com.cassiomolin.example.task.api.model.CreateTaskDetails;
import com.cassiomolin.example.task.api.model.QueryTaskResult;
import com.cassiomolin.example.task.api.model.UpdateTaskStatusDetails;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

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
            .accept("application/json")
            .contentType("application/json")
            .body(createTaskDetails)
        .expect()
            .statusCode(201)
            .header("Location", notNullValue())
        .when()
            .post("/tasks")
        .then()
            .log().all();
    }

    @Test
    public void findTasks() {

        QueryTaskResult[] taskQueryResults =

            given()
                .accept("application/json")
            .expect()
                .statusCode(200)
                .contentType("application/json")
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
                .accept("application/json")
                .pathParam("taskId", 1)
            .expect()
                .statusCode(200)
                .contentType("application/json")
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
    public void deleteTask() {

        given()
            .pathParam("taskId", 1)
        .expect()
            .statusCode(204)
        .when()
            .delete("/tasks/{taskId}")
        .then()
            .log().all();
    }

    @Test
    public void updateTaskStatus() {

        UpdateTaskStatusDetails updateTaskDetails = new UpdateTaskStatusDetails();
        updateTaskDetails.setValue(true);

        given()
            .accept("application/json")
            .contentType("application/json")
            .body(updateTaskDetails)
            .pathParam("taskId", 1)
        .expect()
            .statusCode(204)
        .when()
            .put("/tasks/{taskId}/completed")
        .then()
            .log().all();
    }
}
