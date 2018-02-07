package com.unwe.bugtracker.models.bindingModels.comments;

import com.unwe.bugtracker.entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddCommentModel {

    @NotNull(message = "Comment cannot be null!")
    @Size(min = 2, message = "Company name too short")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
