package com.example.admin.task1.api.util;

import com.example.admin.task1.api.remote.APIClient;
import com.example.admin.task1.api.remote.APIInterface;

/**
 * Created by Admin on 8/28/2017.
 */

public class APIUtil {
    private APIUtil() {
    }

    public static final String API_PRODUCTS = "/projects/learning/laravel/e-commerce-portal/api/v1/getProducts";
    public static final String API_SETTINGS = "/projects/learning/laravel/e-commerce-portal/api/v1/getSettings";
    public static final String API_CATEGORY = "/projects/learning/laravel/e-commerce-portal/api/v1/getProducts?category_id=1";
    public static final String API_BRAND = "/projects/learning/laravel/e-commerce-portal/api/v1/getProducts?brand_id=1";

    public static final String BASE_URL = "http://192.168.1.73";
    public static final String KEY_POSITION = "position";
    public static final String STORED_ITEMS = "productList";
    public static final String ACTIVITY_CHECK = "start";
    public static final String ACTIVITY_MAIN = "From_MainActivity";
    public static final String ACTIVITY_BRAND = "From_BrandsActivity";
    public static final String IMAGE_URL = "http://192.168.1.73/projects/learning/laravel/e-commerce-portal/";

    public static APIInterface getAPI() {
        return APIClient.getClient(BASE_URL).create(APIInterface.class);
    }
}
