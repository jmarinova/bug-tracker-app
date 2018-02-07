package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.entities.Comment;
import com.unwe.bugtracker.entities.Issue;
import com.unwe.bugtracker.models.bindingModels.comments.AddCommentModel;
import com.unwe.bugtracker.repositories.CommentRepository;
import com.unwe.bugtracker.services.CommentService;
import com.unwe.bugtracker.services.IssueService;
import com.unwe.bugtracker.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserService userService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Comment> findAllForIssue(Issue issue) {
       return this.commentRepository.findAllByIssue(issue);
    }

    @Override
    public void add(AddCommentModel addCommentModel, Principal principal, Long issueId) {
        Comment comment = this.modelMapper.map(addCommentModel, Comment.class);
        comment.setAuthor(this.userService.findByUsername(principal.getName()));
        comment.setCreatedOn(new Date());
        comment.setIssue(issueService.findIssueById(issueId));
        this.commentRepository.save(comment);
    }
}
