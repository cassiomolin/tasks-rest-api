package com.cassiomolin.example.task.api;

import com.cassiomolin.example.task.api.model.CreateTaskDetails;
import com.cassiomolin.example.task.api.model.QueryTaskResult;
import com.cassiomolin.example.task.api.model.UpdateTaskStatusDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskResourceTest {

    @LocalServerPort
    private int port;

    private URI uri;

    private Client client;

    @Before
    public void setUp() throws Exception {
        this.uri = new URI("http://localhost:" + port + "/api");
        this.client = ClientBuilder.newClient();
    }

    @Test
    public void createTask() {

        CreateTaskDetails createTaskDetails = new CreateTaskDetails();
        createTaskDetails.setDescription("Pay electricity bill");

        Response response = client.target(uri).path("tasks").request()
                .post(Entity.entity(createTaskDetails, MediaType.APPLICATION_JSON));
        assertEquals(Status.CREATED.getStatusCode(), response.getStatus());

        String location = response.getStringHeaders().getFirst(HttpHeaders.LOCATION);
        assertNotNull(location);
    }

    @Test
    public void findTasks() {

        Response response = client.target(uri).path("tasks").request().accept(MediaType.APPLICATION_JSON).get();
        assertEquals(Status.OK.getStatusCode(), response.getStatus());

        List<QueryTaskResult> queryTaskResults = response.readEntity(new GenericType<List<QueryTaskResult>>() {});
        assertNotNull(queryTaskResults);
    }

    @Test
    public void getTask() {

        Response response = client.target(uri).path("tasks").path("3").request().accept(MediaType.APPLICATION_JSON).get();
        assertEquals(Status.OK.getStatusCode(), response.getStatus());

        QueryTaskResult queryTaskResult = response.readEntity(QueryTaskResult.class);
        assertNotNull(queryTaskResult);
    }


    @Test
    public void deleteTask() {
        Response response = client.target(uri).path("tasks").path("2").request().delete();
        assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void updateTaskStatus() {

        UpdateTaskStatusDetails updateTaskStatusDetails = new UpdateTaskStatusDetails();
        updateTaskStatusDetails.setCompleted(true);

        Response response = client.target(uri).path("tasks").path("1").request().delete();
        assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
