package com.unwe.bugtracker.repositories;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    @Query("SELECT c FROM Company AS c")
    List<Company> getAll();

    Company getById(long id);

    @Query("SELECT DISTINCT products FROM Company")

    List<String> findDistinctProductsForCompany();
}
