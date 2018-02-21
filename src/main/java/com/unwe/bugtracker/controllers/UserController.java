package com.unwe.bugtracker.controllers;

import com.unwe.bugtracker.configuration.OnRegistrationCompleteEvent;
import com.unwe.bugtracker.constants.Constants;
import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.entities.VerificationToken;
import com.unwe.bugtracker.enums.Role;
import com.unwe.bugtracker.models.bindingModels.users.RegistrationModel;
import com.unwe.bugtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @ModelAttribute("roles")
    public Role[] getRoles(){
        return Role.values();
    }


    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegistrationModel registrationModel){

        return "admin-users-add";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult,
                               WebRequest request,
                               Errors errors){
        if(bindingResult.hasErrors()){
            return "admin-users-add";
        }

        User registered = this.userService.register(registrationModel);

        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                (registered, request.getLocale(), appUrl));

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String token,
                               Model model,
                               WebRequest request,
                               Principal principal){
//        Locale locale = request.getLocale();
//
//        VerificationToken verificationToken = this.userService.getVerificationToken(token);
//        if (verificationToken == null) {
//            String message = messages.getMessage("auth.message.invalidToken", null, locale);
////            model.addAttribute("message", message);
////            return "redirect:/login";
//        }
//
//        User user = verificationToken.getUser();
//        Calendar cal = Calendar.getInstance();
//        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            String messageValue = messages.getMessage("auth.message.expired", null, locale);
//            model.addAttribute("message", messageValue);
//            return "redirect:/badUser";
//        }
//
        if(error != null){
            model.addAttribute("error", Constants.INVALID_CREDENTIALS);
        }
//        user.setEnabled(true);
//        this.userService.saveRegisteredUser(user);

        return "login";
    }

    @GetMapping("/unauthorized")
    public String getUnauthorizedPage(){

        return "unauthorized";
    }
}
