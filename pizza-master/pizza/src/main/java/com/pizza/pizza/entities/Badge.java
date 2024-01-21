package com.pizza.pizza.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String badgeName;
    private Integer discountPercentage;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Customer customer;

    public Badge() {
    }

    public Badge(String badgeName, Integer discountPercentage, Customer customer) {
        this.badgeName = badgeName;
        this.discountPercentage = discountPercentage;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
