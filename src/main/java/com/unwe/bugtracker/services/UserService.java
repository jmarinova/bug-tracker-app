package com.unwe.bugtracker.services;

import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.models.bindingModels.users.RegistrationModel;
import com.unwe.bugtracker.models.viewModels.user.AllUsersViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    void register(RegistrationModel registrationModel);

    Boolean getEnabled(long id);

    List<AllUsersViewModel> findAll();

    User findByUsername(String username);
}
