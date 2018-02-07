package com.unwe.bugtracker.repositories;

import com.unwe.bugtracker.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(String username);

    @Query("SELECT u.isEnabled FROM User AS u WHERE u.id LIKE (:userId)")
    Boolean getEnabled(long userId);

    @Query("SELECT u FROM User AS u")
    List<User> findAll();
}
