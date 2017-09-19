package com.example.admin.task1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/15/2017.
 */

public class CartPostRemoveResponse extends GenericResponse
{

    @SerializedName("cartCount")
    @Expose
    private int cartCount;

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

}
