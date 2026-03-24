package com.tinyspring;

@tinyspring.Service
public class ProductService {
    public String getProduct(int id) {
        return "Product #" + id + " - Laptop";
    }
}
