package com.example.admin.task1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/19/2017.
 */

public class Cart
{

    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("product")
    @Expose
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}