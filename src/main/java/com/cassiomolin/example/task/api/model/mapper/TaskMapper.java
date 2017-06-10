package com.cassiomolin.example.task.api.model.mapper;


import com.cassiomolin.example.task.api.model.CreateTaskDetails;
import com.cassiomolin.example.task.api.model.QueryTaskResult;
import com.cassiomolin.example.task.api.model.UpdateTaskDetails;
import com.cassiomolin.example.task.api.model.UpdateTaskStatusDetails;
import com.cassiomolin.example.task.domain.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Component that maps a {@link Task} domain model to API models and vice versa.
 *
 * @author cassiomolin
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    Task toTask(CreateTaskDetails createTaskDetails);

    QueryTaskResult toQueryTaskResult(Task task);

    List<QueryTaskResult> toQueryTaskResults(List<Task> tasks);

    void updateTask(UpdateTaskDetails updateTaskDetails, @MappingTarget Task task);

    @Mapping(source = "value", target = "completed")
    void updateTask(UpdateTaskStatusDetails updateTaskStatusDetails, @MappingTarget Task task);
}