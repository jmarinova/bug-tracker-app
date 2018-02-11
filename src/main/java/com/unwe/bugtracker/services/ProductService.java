package com.unwe.bugtracker.services;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.models.bindingModels.product.AddProductModel;
import com.unwe.bugtracker.models.viewModels.product.AllProductsViewModel;

import java.util.List;
import java.util.Set;

public interface ProductService {
    Set<String> getProductsNames();

    List<Product> getAll();

    void add(AddProductModel addProductModel);

    List<AllProductsViewModel> getAllViewModel();

    List<Product> getAllProductsByCompanies(List<Company> companies);

    Product getById(long id);

    Product findByName(String name);

    Set<String> getAllByCompanies(List<Company> companies);
}
