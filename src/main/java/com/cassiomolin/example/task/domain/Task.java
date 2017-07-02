package com.cassiomolin.example.task.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * JPA entity that represents a task.
 *
 * @author cassiomolin
 */
@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private Boolean completed;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Task() {

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @PrePersist
    void onCreate() {
        this.setCreatedDate(new Date());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        return completed != null ? completed.equals(task.completed) : task.completed == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        return result;
    }
}
