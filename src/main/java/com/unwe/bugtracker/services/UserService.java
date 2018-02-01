package com.unwe.bugtracker.services;

import com.unwe.bugtracker.models.bindingModels.RegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    void register(RegistrationModel registrationModel);
}
