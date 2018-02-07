package com.unwe.bugtracker.models.bindingModels.issues;

import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.enums.IssueType;
import com.unwe.bugtracker.enums.Status;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddIssueBindingModel {
    @Size(min = 3, max = 10, message = "Invalid name")
    private String name;

    @Size(min = 3, max = 100, message = "Invalid description")
    private String description;

    @NotNull(message = "Product cannot be null!")
    private String product;

    @NotNull(message = "Status cannot be null!")
    private Status status;

    @NotNull(message = "Issue cannot be null!")
    private IssueType issueType;

    private User author;

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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
