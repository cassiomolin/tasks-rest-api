package com.cassiomolin.example.task.service;

import com.cassiomolin.example.task.domain.Task;
import com.cassiomolin.example.task.domain.TaskFilter;
import com.cassiomolin.example.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service that provides operations for {@link Task}s.
 *
 * @author cassiomolin
 */
@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Create a task.
     *
     * @param task
     * @return
     */
    public Task createTask(@NotNull Task task) {
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    /**
     * Find all tasks.
     *
     * @return
     */
    public List<Task> findAllTasks() {
        List<Task> list = new ArrayList<>();
        taskRepository.findAll().forEach(list::add);
        return list;
    }

    /**
     * Find tasks using a filter.
     *
     * @param filter
     * @return
     */
    public List<Task> findTasks(@NotNull TaskFilter filter) {

        Task task = new Task();
        task.setDescription(filter.getDescription());
        task.setCompleted(filter.getCompleted());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("description", match -> match.contains().ignoreCase());
        Example<Task> example = Example.of(task, matcher);

        Sort sort = new Sort(Sort.Direction.ASC, "createdDate");

        List<Task> list = new ArrayList<>();
        taskRepository.findAll(example, sort).forEach(list::add);

        return list;
    }

    /**
     * Delete tasks.
     *
     * @param completed
     * @return
     */
    public void deleteTasks(Boolean completed) {

        if (completed == null) {
            taskRepository.deleteAll();
        } else {
            Task task = new Task();
            task.setCompleted(completed);
            Example<Task> example = Example.of(task);
            taskRepository.delete(taskRepository.findAll(example));
        }
    }

    /**
     * Find a task by id.
     *
     * @param taskId
     * @return
     */
    public Optional<Task> findTask(@NotNull Long taskId) {
        return Optional.ofNullable(taskRepository.findOne(taskId));
    }

    /**
     * Update a task.
     *
     * @param task
     */
    public void updateTask(@NotNull Task task) {
        taskRepository.save(task);
    }

    /**
     * Delete a task.
     *
     * @param taskId
     */
    public void deleteTask(@NotNull Long taskId) {
        taskRepository.delete(taskId);
    }
}
