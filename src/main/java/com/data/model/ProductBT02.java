package com.data.model;

public class ProductBT02 {
    private String name;
    private double price;

    public ProductBT02() {}

    public ProductBT02(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
