package com.cassiomolin.example.task.domain;

/**
 * Model that represents a filter for {@link Task}s.
 *
 * @author cassiomolin
 */
public class TaskFilter {

    private String description;
    private Boolean completed;

    public TaskFilter() {

    }

    public String getDescription() {
        return description;
    }

    public TaskFilter setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public TaskFilter setCompleted(Boolean completed) {
        this.completed = completed;
        return this;
    }
}
