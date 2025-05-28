package com.data.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order2 {
    private int orderId;
    private List<Product> orderedProducts;
    private double totalAmount;
    private LocalDateTime orderDate;
    public Order2(int orderId, List<Product> orderedProducts, double totalAmount, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.orderedProducts = orderedProducts;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}