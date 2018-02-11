package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.entities.Comment;
import com.unwe.bugtracker.entities.Issue;
import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.enums.Status;
import com.unwe.bugtracker.models.bindingModels.issues.AddIssueBindingModel;
import com.unwe.bugtracker.models.bindingModels.issues.EditIssueBindingModel;
import com.unwe.bugtracker.models.viewModels.issues.AllIssuesViewModel;
import com.unwe.bugtracker.models.viewModels.issues.IssueViewModel;
import com.unwe.bugtracker.repositories.IssueRepository;
import com.unwe.bugtracker.repositories.ProductRepository;
import com.unwe.bugtracker.services.IssueService;
import com.unwe.bugtracker.services.ProductService;
import com.unwe.bugtracker.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public List<Issue> findAll() {
       return this.issueRepository.findAll();
    }

    @Override
    public List<Issue> findAllByProduct(List<String> products) {
        List<Issue> allIssues = new ArrayList<>();

        for (String product : products) {
            if(product.equals("ALL")){
                continue;
            }
            Product productObj = this.productService.findByName(product);
            List<Issue> currentIssues = this.issueRepository.findAllByProduct(productObj);
            allIssues.addAll(currentIssues);
        }

        return allIssues;
    }

    @Override
    public List<AllIssuesViewModel> allIssuesViewModel(List<String> filters) {

        List<AllIssuesViewModel> allIssuesViewModels = new ArrayList<>();
        List<Issue> issues = new ArrayList<>();

        if (filters == null){
            issues = this.findAll();
        }else{
            issues = this.findAllByProduct(filters);
        }

        for (Issue issue : issues) {
            allIssuesViewModels.add(this.modelMapper.map(issue, AllIssuesViewModel.class));
        }

        return allIssuesViewModels;
    }

    @Override
    public void add(AddIssueBindingModel addIssueBindingModel, Principal principal) {
        Issue issue = this.modelMapper.map(addIssueBindingModel, Issue.class);
        Product product = this.productService.findByName(addIssueBindingModel.getProduct());
        issue.setProduct(product);

        User user = this.userService.findByUsername(principal.getName());
        issue.setAuthor(user);
        issue.setStatus(Status.NEW);
        issue.setCreatedOn(new Date());

        this.issueRepository.save(issue);
    }

    @Override
    public IssueViewModel getById(long id) {
        Issue issue = this.issueRepository.findOne(id);
        return this.modelMapper.map(issue, IssueViewModel.class);
    }

    @Override
    public Issue findIssueById(long id) {
        return this.issueRepository.findOne(id);
    }

    @Override
    public EditIssueBindingModel findById(long id) {
        Issue issue = this.issueRepository.findOne(id);
        return this.modelMapper.map(issue, EditIssueBindingModel.class);
    }

    @Override
    public void update(EditIssueBindingModel editIssueBindingModel) {
        Issue issue = this.modelMapper.map(editIssueBindingModel, Issue.class);
//        User assignedTo = this.userService.findByUsername();
        issue.setAssignedTo(editIssueBindingModel.getAssignedTo());
        this.issueRepository.save(issue);
    }

    @Override
    public void delete(long id) {
        this.issueRepository.delete(id);
    }

}
