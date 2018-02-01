package com.unwe.bugtracker.controllers;

import com.unwe.bugtracker.constants.Constants;
import com.unwe.bugtracker.enums.Role;
import com.unwe.bugtracker.models.bindingModels.RegistrationModel;
import com.unwe.bugtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("roles")
    public Role[] getRoles(){
        return Role.values();
    }


    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegistrationModel registrationModel){

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }

        this.userService.register(registrationModel);

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", Constants.INVALID_CREDENTIALS);
        }

        return "login";
    }

    @GetMapping("/unauthorized")
    public String getUnauthorizedPage(){

        return "unauthorized";
    }
}
