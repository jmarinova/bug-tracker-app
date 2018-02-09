package com.unwe.bugtracker.services;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.models.bindingModels.company.AddCompanyModel;
import com.unwe.bugtracker.models.bindingModels.company.EditCompanyModel;
import com.unwe.bugtracker.models.viewModels.companies.AllCompaniesViewModel;

import java.util.List;

public interface CompanyService {
    List<Company> getAll();

    void add(AddCompanyModel addCompanyModel);

    Company getById(long id);

    EditCompanyModel getEditCompanyById(long id);

    List<AllCompaniesViewModel> getAllViewModel();
}
