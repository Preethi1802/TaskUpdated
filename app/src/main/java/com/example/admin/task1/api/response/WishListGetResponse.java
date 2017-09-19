package com.example.admin.task1.api.response;

import com.example.admin.task1.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 9/15/2017.
 */

public class WishListGetResponse extends GenericResponse
{

    @SerializedName("favoriteCount")
    @Expose
    private int favoriteCount;
    @SerializedName("products")
    @Expose
    private List<Product> products = new ArrayList<>();


    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
