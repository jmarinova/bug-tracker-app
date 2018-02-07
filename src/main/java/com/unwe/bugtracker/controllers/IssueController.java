package com.unwe.bugtracker.controllers;

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
import java.util.List;

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
    public List<String> getProducts(){
        return this.productService.getProductsNames();
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
    public String viewIssues(Model model,@RequestParam(value = "filter", required = false) List<String> filter,
                             @AuthenticationPrincipal Principal principal){
        List<AllIssuesViewModel> allIssues = this.issueService.allIssuesViewModel();
        model.addAttribute("issuesViewModels", allIssues);
//        if(this.userService.findByUsername(principal.getName()).getCompany() != null){
//            List<String> products = this.productService
//
//        }else{
//            List<String> products = this.productService.getAllProductNamesDistinct();
//        }
//        model.addAttribute("products", products);

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

        return "redirect:/issues/add";
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
    public String addIssue(@ModelAttribute AddIssueBindingModel addIssueBindingModel){

        return "issue-add";
    }

}
