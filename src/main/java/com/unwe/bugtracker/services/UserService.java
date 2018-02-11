package com.unwe.bugtracker.services;

import com.unwe.bugtracker.entities.Role;
import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.entities.VerificationToken;
import com.unwe.bugtracker.models.bindingModels.users.RegistrationModel;
import com.unwe.bugtracker.models.viewModels.user.AllUsersViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    User register(RegistrationModel registrationModel);

    Boolean getEnabled(long id);

    List<AllUsersViewModel> findAll();

    User findByUsername(String username);

    List<User> getAllByAuthorities(List<Role> roles);

    void createVerificationToken(User user, String token);

    void saveRegisteredUser(User user);

    VerificationToken getVerificationToken(String VerificationToken);
}
