package com.unwe.bugtracker.services;


import com.unwe.bugtracker.entities.Role;

public interface RoleService {
    Role findByAuthority(String auth);
}
