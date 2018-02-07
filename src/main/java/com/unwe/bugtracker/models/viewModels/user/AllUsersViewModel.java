package com.unwe.bugtracker.models.viewModels.user;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.entities.Role;

import java.util.List;

public class AllUsersViewModel {
    private long id;

    private String username;

    private String fullName;

    private boolean enabled;

    private Company company;

    private List<Role> authorities;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }
}
