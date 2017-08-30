package com.example.admin.task1.api.response;

import android.util.Log;

import com.example.admin.task1.model.Product;

import java.util.ArrayList;

/**
 * Created by Admin on 8/28/2017.
 */

public class ProductResponse
{
    private static final String TAG = "ProductResponse";

    ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        Log.i(TAG,"**********"+products);

        return products;
    }
}
