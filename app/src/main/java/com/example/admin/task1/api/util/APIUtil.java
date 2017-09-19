package com.example.admin.task1.api.util;

import com.example.admin.task1.api.remote.APIClient;
import com.example.admin.task1.api.remote.APIInterface;
import com.example.admin.task1.api.response.GenericResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thapovan.android.commonutils.log.L;

import java.net.HttpURLConnection;

import okhttp3.ResponseBody;

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

    public static final String API_GET_CART = "/projects/learning/laravel/e-commerce-portal/api/v1/getCartN";
    public static final String API_REMOVE_FROM_CART = "/projects/learning/laravel/e-commerce-portal/api/v1/removeFromCart";
    public static final String API_ADD_TO_CART = "/projects/learning/laravel/e-commerce-portal/api/v1/addToCart";

    public static final String API_GET_WHISHLIST = " /projects/learning/laravel/e-commerce-portal/api/v1/getWishlist";
    public static final String API_REMOVE_FROM_WHISHLIST = "/projects/learning/laravel/e-commerce-portal/api/v1/removeFromWishlist";
    public static final String API_ADD_TO_WHISHLIST = "/projects/learning/laravel/e-commerce-portal/api/v1/addToWishlist";


    public static final String BASE_URL = "http://192.168.1.73";

    public static final String IMAGE_URL = "http://192.168.1.73/projects/learning/laravel/e-commerce-portal/";

    public static APIInterface getAPI() {
        return APIClient.getClient(BASE_URL).create(APIInterface.class);
    }

    public static <T extends GenericResponse> T processUnSuccessResponce(int responseCode, ResponseBody responseBody, Class<T> clazz){
        if(responseCode == HttpURLConnection.HTTP_BAD_REQUEST){
            Gson gson = new GsonBuilder().create();
            T errResponse;
            try {
                errResponse = gson.fromJson(responseBody.string(), clazz);
                return errResponse;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return getGenericResponseErr(clazz);
    }

    public static <T extends GenericResponse> T getGenericResponseErr(Class<T> clazz, Throwable t){
        L.d(t);
        return getGenericResponseErr(clazz);
    }

    public static <T extends GenericResponse> T getGenericResponseErr(Class<T> clazz){
        try {
            T response = clazz.newInstance();
            response.setSuccess(false);
//            response.setMessage(AppController.getStr(R.string.server_msg_unable_process));
            response.setMessage("server_msg_unable_process");
            return response;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
