package com.unwe.bugtracker.services;

import com.unwe.bugtracker.entities.Comment;
import com.unwe.bugtracker.entities.Issue;
import com.unwe.bugtracker.models.bindingModels.comments.AddCommentModel;

import java.security.Principal;
import java.util.List;

public interface CommentService {
    List<Comment> findAllForIssue(Issue issue);

    void add(AddCommentModel addCommentModel, Principal principal, Long issueId);
}
