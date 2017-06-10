package com.cassiomolin.example.task.api.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * API model that holds details for creating a task.
 *
 * @author cassiomolin
 */
public class CreateTaskDetails {

    @NotBlank
    private String description;

    public CreateTaskDetails() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
