package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.constants.Constants;
import com.unwe.bugtracker.entities.Role;
import com.unwe.bugtracker.repositories.RoleRepository;
import com.unwe.bugtracker.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByAuthority(String auth) {
       Role role = this.roleRepository.findByAuthority(Constants.ROLE + auth);

       if(role == null){
           Role newRole = new Role();
           newRole.setAuthority(Constants.ROLE + auth);
           this.roleRepository.save(newRole);
           return newRole;
       }

       return role;
    }
}
