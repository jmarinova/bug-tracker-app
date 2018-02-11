package com.unwe.bugtracker.models.bindingModels.users;


import com.unwe.bugtracker.annotations.IsPasswordsMatching;
import com.unwe.bugtracker.entities.Company;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@IsPasswordsMatching
public class RegistrationModel {

    @Size(min = 4, message = "Username too short")
    private String username;

    @Size(min = 4, message = "Password too short")
    private String password;

    @Size(min = 4, message = "Confirm Password shorter than 5 symbols")
    private String confirmPassword;

    @Size(min = 5, message = "Full name too short")
    private String fullName;

    @NotNull(message = "Company cannot be null!")
    private long company;

    @NotNull(message = "Role cannot be null!")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getCompany() {
        return company;
    }

    public void setCompany(long company) {
        this.company = company;
    }
}
