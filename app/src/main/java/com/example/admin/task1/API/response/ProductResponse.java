package com.example.admin.task1.api.response;

import com.example.admin.task1.model.Product;

import java.util.List;

/**
 * Created by Admin on 8/28/2017.
 */

public class ProductResponse extends GenericResponse {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
