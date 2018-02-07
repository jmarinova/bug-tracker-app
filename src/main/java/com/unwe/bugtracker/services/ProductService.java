package com.unwe.bugtracker.services;

import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.models.bindingModels.product.AddProductModel;
import com.unwe.bugtracker.models.viewModels.product.AllProductsViewModel;

import java.util.List;

public interface ProductService {
    List<String> getProductsNames();

    List<Product> getAll();

    void add(AddProductModel addProductModel);

    List<AllProductsViewModel> getAllViewModel();

//    List<String> getAllProductNamesDistinct();

    Product getById(long id);

    Product findByName(String name);
}
