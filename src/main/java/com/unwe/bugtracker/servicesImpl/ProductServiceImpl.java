package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.repositories.ProductRepository;
import com.unwe.bugtracker.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<String> getProductsNames() {
        Iterable<Product> products = this.productRepository.findAll();
        List<String> namesList = new ArrayList<>();

        for (Product product : products) {
            namesList.add(product.getName());
        }

        return namesList;
    }
}
