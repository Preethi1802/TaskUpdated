package com.example.admin.task1.api.remote;


import com.example.admin.task1.api.response.BrandsResponse;
import com.example.admin.task1.api.response.ProductResponse;
import com.example.admin.task1.api.response.SettingsResponse;
import com.example.admin.task1.api.util.APIUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 8/28/2017.
 */

public interface APIInterface
{
    @GET(APIUtil.API_PRODUCTS)
    Call<ProductResponse> getProducts();

    @GET(APIUtil.API_CATEGORY)
    Call<ProductResponse> getProductsByCategory(@Query("category_id") String categoryId);

    @GET(APIUtil.API_BRAND)
    Call<ProductResponse> getProductsByBrand(@Query("brand_id") String brandId);

    @GET(APIUtil.API_SETTINGS)
    Call<SettingsResponse> getSettings();

    @GET(APIUtil.API_SETTINGS)
    Call<BrandsResponse> getBrand();
}
