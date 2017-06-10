package com.cassiomolin.example.task.repository;

import com.cassiomolin.example.task.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Task}s.
 *
 * @author cassiomolin
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long>, QueryByExampleExecutor<Task> {

}
