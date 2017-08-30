package com.example.admin.task1.api.response;

import android.util.Log;

import com.example.admin.task1.model.Brand;

import java.util.ArrayList;

/**
 * Created by Admin on 8/29/2017.
 */

public class BrandsResponse
{
    private static final String TAG = "BrandsResponse";

    ArrayList<Brand> brand = new ArrayList<>();

    public ArrayList<Brand> getBrand() {
        Log.i(TAG,"**********"+brand);
        return brand;
    }
}
