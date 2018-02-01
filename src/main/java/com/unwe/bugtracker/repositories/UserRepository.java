package com.unwe.bugtracker.repositories;

import com.unwe.bugtracker.entities.Issue;
import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.models.bindingModels.RegistrationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(String username);
}
