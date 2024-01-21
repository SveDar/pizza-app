package com.pizza.pizza.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
                    property = "id")
@Entity
@Table(name = "orders")   //OrderTable
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column()
    private Boolean finished = false;

    @OneToMany(mappedBy = "order")
    private Set<OrderMenu> orders;

    private Timestamp orderDate;

    //@Transient   //stava nevidimo za bazata, no sega prosto shte si naprawim edin getter i taka nqma da e pole
    // jakson pri serializaciq ideserializaciq se orientira po getters i setters ne e nujno da ima pole.!

    public Order() {
    }

    public Order(Customer customer, Timestamp orderDate) {
        this.customer = customer;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderMenu> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderMenu> orders) {
        this.orders = orders;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Set<OrderMenu> getOrderMenus(){
        return orders;
    }

    public String getPhoneNumber(){
        return customer.getPhoneNumber();
    }

    public Double getTotalSum(){
        double totalSum = 0.0;
        for(OrderMenu orderMenu:orders){
            totalSum+=(orderMenu.getNumberOf()*orderMenu.getItemPrice());
        }
        return totalSum;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
