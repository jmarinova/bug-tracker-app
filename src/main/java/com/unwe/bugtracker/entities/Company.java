package com.unwe.bugtracker.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String name;

    private String contactName;

    private String contactPhoneNumber;

    private Date startContractDate;

    private Date endContractDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "companies_products",
        joinColumns = @JoinColumn(name="company_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id"))
    private List<Product> products;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users;

    public Company() {
        this.products = new ArrayList<>();
        this.users = new ArrayList<>();
    }

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public void setEndContractDate(Date endContractate) {
        this.endContractDate = endContractate;
    }
}
