package com.example.admin.task1.api.response;

import com.example.admin.task1.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 9/14/2017.
 */

public class CartResponse
{
    /**
     * success : true
     * message : Login Success
     * user : {"id":1,"name":"Karthick","email":"karthi.vels@gmail.com","mobile_number":"9952188185"}
     */

        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("cartCount")
        @Expose
        private int cartCount;
        @SerializedName("products")
        @Expose
        private List<Product> products = new ArrayList<>();

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
