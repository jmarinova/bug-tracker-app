package com.unwe.bugtracker.repositories;

import com.unwe.bugtracker.entities.Comment;
import com.unwe.bugtracker.entities.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findAllByIssue(Issue issue);
}
