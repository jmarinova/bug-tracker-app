package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.entities.Company;
import com.unwe.bugtracker.repositories.CompanyRepository;
import com.unwe.bugtracker.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


}
