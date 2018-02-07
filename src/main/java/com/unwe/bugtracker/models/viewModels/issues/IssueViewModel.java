package com.unwe.bugtracker.models.viewModels.issues;

import com.unwe.bugtracker.entities.Comment;
import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.enums.IssueType;
import com.unwe.bugtracker.enums.Status;

import javax.persistence.EnumType;
import java.util.Date;
import java.util.List;

public class IssueViewModel {
    private long id;

    private String name;

    private String description;

    private Status status;

    private IssueType issueType;

    private Product product;

    private User author;

    private Date createdOn;

    private User assignedTo;

    private List<Comment> comments;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
