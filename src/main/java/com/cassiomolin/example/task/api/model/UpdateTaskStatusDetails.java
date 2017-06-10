package com.cassiomolin.example.task.api.model;

import javax.validation.constraints.NotNull;

/**
 * API model that holds details for updating a task status.
 *
 * @author cassiomolin
 */
public class UpdateTaskStatusDetails {

    @NotNull
    private Boolean value;

    public UpdateTaskStatusDetails() {

    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
