package com.pizza.pizza.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pizza.pizza.entities.keys.OrderMenuKey;

import javax.persistence.*;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
public class OrderMenu {
    @EmbeddedId
    private OrderMenuKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private Integer numberof;

    public OrderMenu() {
    }

    public OrderMenu(OrderMenuKey id, Order order, Menu menu, Integer numberof) {
        this.id = id;
        this.order = order;
        this.menu = menu;
        this.numberof = numberof;
    }

    public OrderMenuKey getId() {
        return id;
    }

    public void setId(OrderMenuKey id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getNumberOf() {
        return numberof;
    }

    public void setNumberOf(Integer numberof) {
        this.numberof = numberof;
    }

    public Double getItemPrice(){
        return menu.getPrice();
    }
    public  String getItemName(){
        return menu.getItem();
    }
}
