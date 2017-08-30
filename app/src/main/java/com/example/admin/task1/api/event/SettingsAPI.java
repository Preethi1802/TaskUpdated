package com.example.admin.task1.api.event;

import com.example.admin.task1.api.response.BrandsResponse;
import com.example.admin.task1.api.response.SettingsResponse;
import com.example.admin.task1.api.subscriber.SettingsEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 8/28/2017.
 */

public class SettingsAPI extends APIAbstact {

    private SettingsAPI() {
        // empty method
    }

    public static void get(final SettingsEventSubscriber subscriber) {

        sApiInterface.getSettings().enqueue(new Callback<SettingsResponse>() {
            @Override
            public void onResponse(Call<SettingsResponse> call, Response<SettingsResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onSettingsCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<SettingsResponse> call, Throwable t) {
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }

    public static void getCategoryListByBrand(final SettingsEventSubscriber subscriber) {

        sApiInterface.getBrand().enqueue(new Callback<BrandsResponse>() {
            @Override
            public void onResponse(Call<BrandsResponse> call, Response<BrandsResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onBrandCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<BrandsResponse> call, Throwable t) {
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }
}

