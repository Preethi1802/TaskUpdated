package com.example.admin.task1.API.response;

import android.util.Log;

import com.example.admin.task1.model.Category;
import com.example.admin.task1.model.Product;

/**
 * Created by Admin on 8/17/2017.
 */

public class ApiResponse
{
    private static final String TAG = "ApiResponse";

    Product[] products;

    public Product[] getProducts() {
        return products;
    }

    Category[] category;

    public Category[] getCategory() {
        Log.i(TAG,"len"+category);
        return category;
    }


}
