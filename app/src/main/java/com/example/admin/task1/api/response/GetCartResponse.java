package com.example.admin.task1.api.response;

import com.example.admin.task1.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 9/14/2017.
 */

public class GetCartResponse extends GenericResponse
{
    @SerializedName("cartCount")
    @Expose
    private int cartCount;
    @SerializedName("products")
    @Expose
    private List<Product> products = new ArrayList<>();

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
