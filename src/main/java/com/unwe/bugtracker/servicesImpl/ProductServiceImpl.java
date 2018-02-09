package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.models.bindingModels.product.AddProductModel;
import com.unwe.bugtracker.models.viewModels.product.AllProductsViewModel;
import com.unwe.bugtracker.repositories.ProductRepository;
import com.unwe.bugtracker.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<String> getProductsNames() {
        Iterable<Product> products = this.productRepository.findAll();
        Set<String> namesList = new HashSet<>();

        for (Product product : products) {
            namesList.add(product.getName());
        }

        return namesList;
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        Iterable<Product> productIterable = this.productRepository.findAll();

        for (Product product : productIterable) {
            productList.add(product);
        }

        return productList;
    }

    @Override
    public void add(AddProductModel addProductModel) {
        Product product = this.modelMapper.map(addProductModel, Product.class);
        this.productRepository.save(product);
    }

    @Override
    public List<AllProductsViewModel> getAllViewModel() {
        List<Product> productList = getAll();
        List<AllProductsViewModel> allProductsViewModels = new ArrayList<>();

        for (Product product : productList) {
            allProductsViewModels.add(this.modelMapper.map(product, AllProductsViewModel.class));
        }

        return allProductsViewModels;
    }

//    @Override
//    public List<String> getAllProductNamesDistinct() {
//        List<Product> products = this.productRepository.findDistinctByName();
//
//        List<String> result = new ArrayList<>();
//
//        for (Product product : products) {
//            result.add(product.getName());
//        }
//
//        return result;
//    }

    @Override
    public Product getById(long id) {
        return this.productRepository.getById(id);
    }

    @Override
    public Product findByName(String name) {
        Product product = this.productRepository.findFirstByName(name);
        System.out.println();
        return product;
    }

    @Override
    public List<String> getAllByCompanies(List<Company> companies) {
        List<Product> products = this.productRepository.getDistinctByCompanies(companies);
        List<String> names = new ArrayList<>();

        for (Product product : products) {
            names.add(product.getName());
        }

        return names;
    }
}
