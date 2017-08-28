package com.example.admin.task1.API.request;

import com.example.admin.task1.API.response.SettingsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Admin on 8/28/2017.
 */

public interface SettingsRequest
{
    @GET("/Preethi1802/701b59bc60b60046ff877bdf259bff9b/raw/5b8d9d83a4ee81a0e0279c0fdd9b72ebe675b1d8/getSettings")
    Call<SettingsResponse> getCategoriesdetail();
}
