package com.example.admin.task1.API.request;

import com.example.admin.task1.API.response.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 8/14/2017.
 */

public interface ApiInterface {
    @GET("/Preethi1802/47cd2bc5bc7c783f9230fc27a5e74c0e/raw/5877bf3cb85c4eb9db661a9fb25db177c4013f1a/getProducts")
    Call<ApiResponse> getProductDetails();

    @GET("/Preethi1802/701b59bc60b60046ff877bdf259bff9b/raw/5b8d9d83a4ee81a0e0279c0fdd9b72ebe675b1d8/getSettings")
    Call<ApiResponse> getCategoriesdetail();


}
