package com.cassiomolin.example.task.api.model;

import javax.validation.constraints.NotNull;

/**
 * API model that holds details for updating a task status.
 *
 * @author cassiomolin
 */
public class UpdateTaskStatusDetails {

    @NotNull
    private Boolean completed;

    public UpdateTaskStatusDetails() {

    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
