package com.unwe.bugtracker.repositories;

import com.unwe.bugtracker.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
    Role findByAuthority(String authority);
}