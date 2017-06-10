package com.cassiomolin.example.task.api.model;

/**
 * API model for returning a task query result.
 *
 * @author cassiomolin
 */
public class QueryTaskResult {

    private Long id;

    private String description;

    private Boolean completed;

    public QueryTaskResult() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
