package com.example.admin.task1.API.response;

import android.util.Log;

import com.example.admin.task1.model.Category;

import java.util.ArrayList;

/**
 * Created by Admin on 8/28/2017.
 */

public class SettingsResponse
{
    private static final String TAG = "ApiResponse";

    ArrayList<Category> category;

    public ArrayList<Category> getCategory() {
        Log.i(TAG,"len"+category);
        return category;
    }

}
