package com.unwe.bugtracker.models.bindingModels.company;

import com.unwe.bugtracker.entities.Product;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class EditCompanyModel {
    @Size(min = 2, message = "Company name too short")
    private String name;

    @Size(min = 5, message = "Contact name too short")
    private String contactName;

    @Size(min = 5, message = "Phone number too short")
    private String contactPhoneNumber;

    @NotNull(message = "Date cannot be null!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startContractDate;

    @NotNull(message = "Date cannot be null!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endContractDate;

    @NotNull(message = "At least one product should be selected!")
    private List<Product> products;

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
