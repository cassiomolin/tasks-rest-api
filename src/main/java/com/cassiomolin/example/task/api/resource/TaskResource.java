package com.cassiomolin.example.task.api.resource;


import com.cassiomolin.example.task.api.model.CreateTaskDetails;
import com.cassiomolin.example.task.api.model.QueryTaskResult;
import com.cassiomolin.example.task.api.model.UpdateTaskDetails;
import com.cassiomolin.example.task.api.model.UpdateTaskStatusDetails;
import com.cassiomolin.example.task.api.model.mapper.TaskMapper;
import com.cassiomolin.example.task.domain.Task;
import com.cassiomolin.example.task.domain.TaskFilter;
import com.cassiomolin.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Component that exposes REST API endpoints for {@link Task}s.
 *
 * @author cassiomolin
 */
@Component
@Path("tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @POST
    public Response createTask(@Valid @NotNull CreateTaskDetails createTaskDetails) {
        Task task = taskMapper.toTask(createTaskDetails);
        task = taskService.createTask(task);
        URI uri = uriInfo.getAbsolutePathBuilder().path(task.getId().toString()).build();
        return Response.created(uri).build();
    }

    @GET
    public Response findTasks(@QueryParam("description") String description,
                              @QueryParam("completed") Boolean completed) {
        TaskFilter filter = new TaskFilter().setDescription(description).setCompleted(completed);
        List<Task> tasks = taskService.findTasks(filter);
        List<QueryTaskResult> queryTaskResults = taskMapper.toQueryTaskResults(tasks);
        return Response.ok(queryTaskResults).build();
    }

    @DELETE
    public Response deleteTasks(@QueryParam("completed") Boolean completed) {
        taskService.deleteTasks(completed);
        return Response.noContent().build();
    }

    @GET
    @Path("{taskId}")
    public Response getTask(@PathParam("taskId") Long taskId) {
        Task task = taskService.findTask(taskId).orElseThrow(NotFoundException::new);
        QueryTaskResult queryTaskResult = taskMapper.toQueryTaskResult(task);
        return Response.ok(queryTaskResult).build();
    }

    @PUT
    @Path("{taskId}")
    public Response updateTask(@PathParam("taskId") Long taskId,
                               @Valid @NotNull UpdateTaskDetails updateTaskDetails) {
        Task task = taskService.findTask(taskId).orElseThrow(NotFoundException::new);
        taskMapper.updateTask(updateTaskDetails, task);
        taskService.updateTask(task);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{taskId}")
    public Response deleteTask(@PathParam("taskId") Long taskId) {
        taskService.findTask(taskId).orElseThrow(NotFoundException::new);
        taskService.deleteTask(taskId);
        return Response.noContent().build();
    }

    @PUT
    @Path("{taskId}/completed")
    public Response updateTaskStatus(@PathParam("taskId") Long taskId,
                                     @Valid @NotNull UpdateTaskStatusDetails updateTaskStatusDetails) {
        Task task = taskService.findTask(taskId).orElseThrow(NotFoundException::new);
        taskMapper.updateTask(updateTaskStatusDetails, task);
        taskService.updateTask(task);
        return Response.noContent().build();
    }
}
