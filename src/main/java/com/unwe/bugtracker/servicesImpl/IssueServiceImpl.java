package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.entities.Issue;
import com.unwe.bugtracker.repositories.IssueRepository;
import com.unwe.bugtracker.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public List<Issue> findAll() {
       return this.issueRepository.findAll();
    }
}
