package com.example.admin.task1.api.util;

import com.example.admin.task1.api.remote.APIClient;
import com.example.admin.task1.api.remote.APIInterface;

/**
 * Created by Admin on 8/28/2017.
 */

public class APIUtil
{
    private APIUtil(){}

    public static final String API_PRODUCTS	 = "/Preethi1802/47cd2bc5bc7c783f9230fc27a5e74c0e/raw/5877bf3cb85c4eb9db661a9fb25db177c4013f1a/getProducts";
    public static final String API_SETTINGS	 = "/Preethi1802/701b59bc60b60046ff877bdf259bff9b/raw/5b8d9d83a4ee81a0e0279c0fdd9b72ebe675b1d8/getSettings";
    public static final String BASE_URL      = "https://gist.githubusercontent.com";
    public static final String KEY_POSITION  = "position";
    public static final String STORED_ITEMS  = "productList";
    public static final String IMAGE_URL     = "http://192.168.1.73/projects/learning/laravel/e-commerce-portal/";

    public static APIInterface getAPI() {
        return APIClient.getClient(BASE_URL).create(APIInterface.class);
    }
}
