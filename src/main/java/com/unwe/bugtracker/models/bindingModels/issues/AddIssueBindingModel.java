package com.unwe.bugtracker.models.bindingModels.issues;

import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.enums.IssueType;
import com.unwe.bugtracker.enums.Severity;
import com.unwe.bugtracker.enums.Status;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddIssueBindingModel {
    @Size(min = 3, max = 80, message = "Invalid name")
    private String name;

    @Size(min = 3, max = 100, message = "Invalid description")
    private String description;

    @NotNull(message = "Product cannot be null!")
    private Product product;

    @NotNull(message = "Severity cannot be null!")
    private Severity severity;

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

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
