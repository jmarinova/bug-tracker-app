package com.unwe.bugtracker.repositories;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT p.name FROM Product AS p")
    Set<String> getProductsNames();

    Product getById(long id);

    List<Product> getAllByCompaniesIn(List<Company> companies);

    List<Product> getDistinctByCompanies(List<Company> companies);

    Product findFirstByName(String name);

//    List<Product> findDistinctByName();
}
