package com.unwe.bugtracker.controllers;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.enums.Role;
import com.unwe.bugtracker.models.bindingModels.company.AddCompanyModel;
import com.unwe.bugtracker.models.bindingModels.company.EditCompanyModel;
import com.unwe.bugtracker.models.bindingModels.product.AddProductModel;
import com.unwe.bugtracker.models.bindingModels.users.RegistrationModel;
import com.unwe.bugtracker.models.viewModels.companies.AllCompaniesViewModel;
import com.unwe.bugtracker.models.viewModels.product.AllProductsViewModel;
import com.unwe.bugtracker.models.viewModels.user.AllUsersViewModel;
import com.unwe.bugtracker.services.CompanyService;
import com.unwe.bugtracker.services.ProductService;
import com.unwe.bugtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProductService productService;

    @ModelAttribute("companies")
    public List<Company> getCompanies(){
        List<Company> allCompanies = this.companyService.getAll();
        return allCompanies;
    }

    @ModelAttribute("products")
    public List<Product> getProducts(){
        return this.productService.getAll();
    }

    @ModelAttribute("roles")
    public Role[] getRoles(){
        return Role.values();
    }


    @GetMapping("/admin/home")
    public String getAdminHomePage(){
        return "admin-home";
    }

    @GetMapping("/admin/users")
    public String viewUsers(Model model){
        List<AllUsersViewModel> userList = this.userService.findAll();
        model.addAttribute("users", userList);

        return "user-view";
    }

    @PostMapping("/admin/users/add")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin-users-add";
        }

        this.userService.register(registrationModel);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/add")
    public String getRegisterUserPage(@ModelAttribute RegistrationModel registrationModel){

        return "admin-users-add";
    }

    @GetMapping("/admin/companies/edit/{id}")
    public String getEditCompanyPage(Model model,
                                    @PathVariable long id){
        model.addAttribute("editCompanyModel", this.companyService.getEditCompanyById(id));
        return "admin-companies-edit";
    }

    @PostMapping("/admin/companies/edit/{id}")
    public String editCompanyPage(@Valid @ModelAttribute EditCompanyModel editCompanyModel, BindingResult bindingResult,
                                    @PathVariable long id){
        if(bindingResult.hasErrors()){
            return "admin-companies-edit";
        }
        this.companyService.update(editCompanyModel);

        return "redirect:/admin/companies";
    }

    @GetMapping("/admin/companies/add")
    public String getAddCompanyPage(@ModelAttribute AddCompanyModel addCompanyModel){
        return "admin-companies-add";
    }

    @PostMapping("/admin/companies/add")
    public String addCompany(@Valid @ModelAttribute AddCompanyModel addCompanyModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin-companies-add";
        }

        this.companyService.add(addCompanyModel);

        return "redirect:/admin/companies";
    }

    @GetMapping("/admin/companies")
    public String viewCompanies(Model model){
        List<AllCompaniesViewModel> companiesViewModels = this.companyService.getAllViewModel();
        model.addAttribute("companiesViewModels", companiesViewModels);

        return "admin-companies";
    }

    @GetMapping("/admin/products/add")
    public String getAddProductPage(@ModelAttribute AddProductModel addProductModel){
        return "admin-products-add";
    }

    @PostMapping("/admin/products/add")
    public String addProduct(@Valid @ModelAttribute AddProductModel addProductModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin-products-add";
        }

        this.productService.add(addProductModel);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products")
    public String viewAllProducts(Model model){
        model.addAttribute("allProductsViewModel", this.productService.getAllViewModel());

        return "admin-products";
    }
}
