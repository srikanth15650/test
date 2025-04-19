package com.example.demo;

import java.util.List;

public class Order{
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Order setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    OrderStatus orderStatus;
    List<Product> getLineItems(){
        return null;
    }
}
