package com.unwe.bugtracker.controllers;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.enums.IssueType;
import com.unwe.bugtracker.enums.Status;
import com.unwe.bugtracker.models.bindingModels.comments.AddCommentModel;
import com.unwe.bugtracker.models.bindingModels.issues.AddIssueBindingModel;
import com.unwe.bugtracker.models.viewModels.issues.AllIssuesViewModel;
import com.unwe.bugtracker.models.viewModels.issues.IssueViewModel;
import com.unwe.bugtracker.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class IssueController {

    private final IssueService issueService;

    private final ProductService productService;

    private final UserService userService;

    private final CompanyService companyService;

    private final CommentService commentService;

    @Autowired
    public IssueController(ProductService productService, IssueService issueService,
                           UserService userService, CompanyService companyService,
                           CommentService commentService) {
        this.issueService = issueService;
        this.productService = productService;
        this.userService = userService;
        this.companyService = companyService;
        this.commentService = commentService;
    }

    @ModelAttribute(name = "products")
    public List<String> getProducts(@AuthenticationPrincipal Principal principal){
        List<String> products = new ArrayList<>();
        if(this.userService.findByUsername(principal.getName()) == null){
            return products;
        }

        if(!this.userService.findByUsername(principal.getName()).getUsername().equals("admin")
                && this.userService.findByUsername(principal.getName())!= null){
            List<Company> companies = new ArrayList<>();
            companies.add(this.userService.findByUsername(principal.getName()).getCompany());

            products = this.productService.getAllByCompanies(companies);
//            model.addAttribute("products", products);

        }else{
            Set<String> productsSet = this.productService.getProductsNames();
            products.addAll(productsSet);
//            model.addAttribute("products", products);
        }

        products.add("ALL");

        return products;
    }

    @ModelAttribute(name = "statuses")
    public Status[] getStatuses(){
        return Status.values();
    }

    @ModelAttribute(name = "issueTypes")
    public IssueType[] getIssueTypes(){
        return IssueType.values();
    }

    @GetMapping("/issues")
    public String viewIssues(Model model,
                             @ModelAttribute ArrayList<String> products,
                             @RequestParam(value = "filter", required = false) List<String> filter,
                             @AuthenticationPrincipal Principal principal){

        List<String> filterForIssues = new ArrayList<>();
        if (filter == null || (filter != null && filter.indexOf("ALL") > -1)){
            filterForIssues = (List<String>) model.asMap().get("products");
        }else{
            filterForIssues = filter;
        }

        List<AllIssuesViewModel> allIssues = this.issueService.allIssuesViewModel(filterForIssues);

        model.addAttribute("issuesViewModels", allIssues);
        model.addAttribute("currentUser", this.userService.findByUsername(principal.getName()));

        return "issues-view";
    }

    @GetMapping("/issues/view/{id}")
    public String getIssueViewPage(Model model,
                                   @PathVariable Long id,
                                   @ModelAttribute AddCommentModel commentModel){
        IssueViewModel issueViewModel = this.issueService.getById(id);
        model.addAttribute("issueViewModel", issueViewModel);
        model.addAttribute("commentModel", new AddCommentModel());
        return "issue-view";
    }

    @PostMapping("/issues/add")
    public String getAddIssuePage(@Valid @ModelAttribute AddIssueBindingModel addIssueBindingModel,
                                  BindingResult bindingResult,
                                  @AuthenticationPrincipal Principal principal){
        if(bindingResult.hasErrors()){
            return "issue-add";
        }
        System.out.println(principal.getName());

        this.issueService.add(addIssueBindingModel, principal);

        return "redirect:/issues";
    }

    @PostMapping("/issues/view/{id}")
    public String addComment(@Valid @ModelAttribute AddCommentModel commentModel,
                             @RequestParam(value = "comment",required = false) String comment,
                             @PathVariable Long id,
                             BindingResult bindingResult,
                             @AuthenticationPrincipal Principal principal){
        if(bindingResult.hasErrors()){
            return "issue-view";
        }

        if(comment != null){
            this.commentService.add(commentModel, principal, id);
        }

        return "redirect:/issues/view/{id}";
    }

    @GetMapping("/issues/add")
    public String addIssue(Model model, @ModelAttribute AddIssueBindingModel addIssueBindingModel){
        List<String> products = (List<String>) model.asMap().get("products");
        products.remove("ALL");

        return "issue-add";
    }

}
