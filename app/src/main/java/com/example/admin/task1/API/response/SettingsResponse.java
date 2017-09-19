package com.example.admin.task1.api.response;

import com.example.admin.task1.model.Brand;
import com.example.admin.task1.model.Category;

import java.util.ArrayList;

/**
 * Created by Admin on 8/28/2017.
 */

public class SettingsResponse extends GenericResponse {

    ArrayList<Category> category;
    ArrayList<Brand> brand;

    public ArrayList<Category> getCategory() {
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

}





