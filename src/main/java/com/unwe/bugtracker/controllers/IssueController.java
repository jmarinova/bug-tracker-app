package com.unwe.bugtracker.controllers;

import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.enums.IssueType;
import com.unwe.bugtracker.enums.Status;
import com.unwe.bugtracker.models.bindingModels.AddIssueBindingModel;
import com.unwe.bugtracker.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class IssueController {

    private final ProductService productService;

    @Autowired
    public IssueController(ProductService productService) {
        this.productService = productService;
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
    public String viewIssues(@ModelAttribute AddIssueBindingModel addIssueBindingModel){
//        List<String> products = this.productService.getProductsNames();
//        for (String product : products) {
//            System.out.println(product);
//        }
//        model.addAttribute("products", products);
        return "issue-add";
    }

    @GetMapping("/issues/add")
    public String addIssue(@ModelAttribute AddIssueBindingModel addIssueBindingModel){

        return "issue-add";
    }
}
