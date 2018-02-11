package com.unwe.bugtracker.models.bindingModels.issues;

import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.enums.IssueType;
import com.unwe.bugtracker.enums.Severity;
import com.unwe.bugtracker.enums.Status;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditIssueBindingModel {

    private long id;

    @Size(min = 3, max = 100, message = "Invalid name")
    private String name;

    @Size(min = 3, max = 200, message = "Invalid description")
    private String description;

    @NotNull(message = "Status cannot be null!")
    private Status status;

    @NotNull(message = "Severity cannot be null!")
    private Severity severity;

    @NotNull(message = "Issue cannot be null!")
    private IssueType issueType;

    private User assignedTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
