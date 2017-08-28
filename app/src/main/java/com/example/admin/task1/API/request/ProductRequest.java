package com.example.admin.task1.API.request;

import com.example.admin.task1.API.response.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 8/14/2017.
 */

public interface ProductRequest {
    @GET("/Preethi1802/47cd2bc5bc7c783f9230fc27a5e74c0e/raw/5877bf3cb85c4eb9db661a9fb25db177c4013f1a/getProducts")
    Call<ProductResponse> getProductDetails();
}
