package com.example.admin.task1.api.response;

import android.util.Log;

import com.example.admin.task1.model.Brand;
import com.example.admin.task1.model.Category;

import java.util.ArrayList;

/**
 * Created by Admin on 8/28/2017.
 */

public class SettingsResponse {
    /**
     * success : true
     * message : Master Service
     */
    private static final String TAG = "SettingsResponse";
    private boolean success;
    private String message;
    ArrayList<Category> category;
    ArrayList<Brand> brand;

    public ArrayList<Category> getCategory() {
        Log.i(TAG, "len" + category);
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }


    public ArrayList<Brand> getBrand() {
        return brand;
    }

    public void setBrand(ArrayList<Brand> brand) {
        this.brand = brand;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

     /* private static final String TAG = "SettingsResponse";

    ArrayList<Category> category;

    public ArrayList<Category> getCategory() {
        Log.i(TAG,"Hiiiiii");
        Log.i(TAG,"len"+category);
        return category;
    }*/


}





