package com.unwe.bugtracker.repositories;

import com.unwe.bugtracker.entities.Issue;
import com.unwe.bugtracker.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>{

    @Query("SELECT i FROM Issue AS i")
    List<Issue> findAll();

    List<Issue> findAllByProduct(Product product);

    Issue findById(long id);
}
