package com.example.admin.task1.api.remote;


import com.example.admin.task1.api.request.CartRequest;
import com.example.admin.task1.api.request.FirebaseLoginRequest;
import com.example.admin.task1.api.request.LoginRequest;
import com.example.admin.task1.api.request.RegistrationRequest;
import com.example.admin.task1.api.request.WishlistRequest;
import com.example.admin.task1.api.response.CartPostAddResponse;
import com.example.admin.task1.api.response.WishListPostAddResponse;
import com.example.admin.task1.api.response.CartGetResponse;
import com.example.admin.task1.api.response.FirebaseLoginResponse;
import com.example.admin.task1.api.response.LoginResponse;
import com.example.admin.task1.api.response.ProductResponse;
import com.example.admin.task1.api.response.RegistrationResponse;
import com.example.admin.task1.api.response.CartPostRemoveResponse;
import com.example.admin.task1.api.response.WishListPostRemoveResponse;
import com.example.admin.task1.api.response.SettingsResponse;
import com.example.admin.task1.api.response.WishListGetResponse;
import com.example.admin.task1.api.util.APIUtil;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Admin on 8/28/2017.
 */

public interface APIInterface {
    @GET(APIUtil.API_PRODUCTS)
    Call<ProductResponse> getProducts(@Query("page") int page);

    @GET(APIUtil.API_PRODUCTS)
    Call<ProductResponse> getProductsByCategory(@Query("category_id") int categoryID);

    @GET(APIUtil.API_PRODUCTS)
    Call<ProductResponse> getProductsByBrand(@Query("brand_id") int brandID);

    @GET(APIUtil.API_SETTINGS)
    Call<SettingsResponse> getCategory();

    @GET(APIUtil.API_SETTINGS)
    Call<SettingsResponse> getBrand();

    @POST(APIUtil.API_LOGIN)
    Call<LoginResponse> postLoginDetails(@Body LoginRequest request);

    @POST(APIUtil.API_REGISTRATION)
    Call<RegistrationResponse> postRegistrationDetails(@Body RegistrationRequest request);

    @POST(APIUtil.API_FIREBASE_LOGIN)
    Call<FirebaseLoginResponse> postFirebaseLoginDetails(@Body FirebaseLoginRequest request);

    @GET(APIUtil.API_GET_CART)
    Call<CartGetResponse> getCart(@Query("user_id") int userID);

    @POST(APIUtil.API_ADD_TO_CART)
    Call<CartPostAddResponse> postAddCart(@Body CartRequest request);

    @POST(APIUtil.API_REMOVE_FROM_CART)
    Call<CartPostRemoveResponse> postRemoveCart(@Body CartRequest request);

    @GET(APIUtil.API_GET_WHISHLIST)
    Call<WishListGetResponse> getWhishlistProducts(@Query("user_id") int userID);

    @POST(APIUtil.API_ADD_TO_WHISHLIST)
    Call<WishListPostAddResponse> postAddProductsToWhishlist(@Body WishlistRequest request);

    @POST(APIUtil.API_REMOVE_FROM_WHISHLIST)
    Call<WishListPostRemoveResponse> postRemoveFromWhishlist(@Body WishlistRequest request);
}
