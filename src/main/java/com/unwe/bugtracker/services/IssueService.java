package com.unwe.bugtracker.services;

import com.unwe.bugtracker.entities.Issue;

import java.util.List;

public interface IssueService {
    List<Issue> findAll();
}
