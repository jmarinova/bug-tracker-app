package com.unwe.bugtracker.repositories;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT p.name FROM Product AS p")
    List<String> getProductsNames();

    Product getById(long id);

    List<Product> getAllByCompaniesIn(List<Company> companies);

    Product findByName(String name);

//    List<Product> findDistinctByName();
}
