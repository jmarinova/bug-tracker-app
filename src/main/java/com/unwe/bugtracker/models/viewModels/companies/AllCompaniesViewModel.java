package com.unwe.bugtracker.models.viewModels.companies;

import com.unwe.bugtracker.entities.Product;

import java.util.Date;
import java.util.List;

public class AllCompaniesViewModel {

    private long id;

    private String name;

    private String contactName;

    private String contactPhoneNumber;

    private Date startContractDate;

    private Date endContractDate;

    private List<Product> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public Date getStartContractDate() {
        return startContractDate;
    }

    public void setStartContractDate(Date startContractDate) {
        this.startContractDate = startContractDate;
    }

    public Date getEndContractDate() {
        return endContractDate;
    }

    public void setEndContractDate(Date endContractDate) {
        this.endContractDate = endContractDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
