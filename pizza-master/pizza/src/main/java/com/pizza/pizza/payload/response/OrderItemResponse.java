package com.pizza.pizza.payload.response;

public class OrderItemResponse {
    private String item;
    private Double price;
    private Integer numberof;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOf() {
        return numberof;
    }

    public void setNumberOf(Integer numberof) {
        this.numberof = numberof;
    }
}
