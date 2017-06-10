package com.cassiomolin.example.task.api.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * API model that holds details for updating a task.
 *
 * @author cassiomolin
 */
public class UpdateTaskDetails {

    @NotBlank
    private String description;

    @NotNull
    private Boolean completed;

    public UpdateTaskDetails() {

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
