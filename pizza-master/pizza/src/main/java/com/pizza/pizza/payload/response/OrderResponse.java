package com.pizza.pizza.payload.response;

import java.sql.Timestamp;
import java.util.List;

public class OrderResponse {
    private String phoneNumber;
    private Timestamp orderDate;
    private Double totalSum;
    private List<OrderItemResponse> orderItems;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String teleNumber) {
        this.phoneNumber = teleNumber;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }
}
