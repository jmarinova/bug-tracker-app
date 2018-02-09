package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.entities.Product;
import com.unwe.bugtracker.models.bindingModels.company.AddCompanyModel;
import com.unwe.bugtracker.models.bindingModels.company.EditCompanyModel;
import com.unwe.bugtracker.models.viewModels.companies.AllCompaniesViewModel;
import com.unwe.bugtracker.models.viewModels.product.AllProductsViewModel;
import com.unwe.bugtracker.repositories.CompanyRepository;
import com.unwe.bugtracker.services.CompanyService;
import com.unwe.bugtracker.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Company> getAll() {
        return this.companyRepository.getAll();
    }

    @Override
    public void add(AddCompanyModel addCompanyModel) {
        Company company = this.modelMapper.map(addCompanyModel, Company.class);
        List<Long> productsIds = addCompanyModel.getProducts();
        List<Product> productList = new ArrayList<>();

        for (long id : productsIds) {
            Product product = this.productService.getById(id);
            productList.add(product);
        }
        company.setProducts(productList);

        this.companyRepository.save(company);
    }



    @Override
    public Company getById(long id) {
        return this.companyRepository.getById(id);
    }

    @Override
    public EditCompanyModel getEditCompanyById(long id) {
        Company company = getById(id);
        EditCompanyModel editCompanyModel = this.modelMapper.map(company, EditCompanyModel.class);

        return  editCompanyModel;
    }

    @Override
    public List<AllCompaniesViewModel> getAllViewModel() {
        List<Company> companies = this.companyRepository.getAll();
        List <AllCompaniesViewModel> allCompaniesViewModels = new ArrayList<>();

        for (Company company : companies) {
            allCompaniesViewModels.add(this.modelMapper.map(company, AllCompaniesViewModel.class));
        }

        return allCompaniesViewModels;
    }
}
