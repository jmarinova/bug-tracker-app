package com.unwe.bugtracker.services;

import com.unwe.bugtracker.entities.Issue;
import com.unwe.bugtracker.models.bindingModels.issues.AddIssueBindingModel;
import com.unwe.bugtracker.models.bindingModels.issues.EditIssueBindingModel;
import com.unwe.bugtracker.models.viewModels.issues.AllIssuesViewModel;
import com.unwe.bugtracker.models.viewModels.issues.IssueViewModel;

import java.security.Principal;
import java.util.List;

public interface IssueService {
    List<Issue> findAll();

    List<Issue> findAllByProduct(List<String> products);

    List<AllIssuesViewModel> allIssuesViewModel(List<String> filters);

    void add(AddIssueBindingModel addIssueBindingModel, Principal principal);

    IssueViewModel getById(long id);

    Issue findIssueById(long id);

    EditIssueBindingModel findById(long id);

    void update(EditIssueBindingModel editIssueBindingModel);

    void delete(long id);
}
