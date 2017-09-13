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
    public static final String API_LOGIN = "/projects/learning/laravel/e-commerce-portal/api/v1/login";
    public static final String API_REGISTRATION = "/projects/learning/laravel/e-commerce-portal/api/v1/registration";
    public static final String API_FIREBASE_LOGIN = "/projects/learning/laravel/e-commerce-portal/api/v1/loginSocial";
    public static final String BASE_URL = "http://192.168.1.73";

    public static final String IMAGE_URL = "http://192.168.1.73/projects/learning/laravel/e-commerce-portal/";

    public static APIInterface getAPI() {
        return APIClient.getClient(BASE_URL).create(APIInterface.class);
    }
}
