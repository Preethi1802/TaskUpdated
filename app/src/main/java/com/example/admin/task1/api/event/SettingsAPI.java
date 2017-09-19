package com.example.admin.task1.api.event;

import android.util.Log;

import com.example.admin.task1.api.response.SettingsResponse;
import com.example.admin.task1.api.subscriber.SettingsEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.admin.task1.api.util.APIUtil.getGenericResponseErr;
import static com.example.admin.task1.api.util.APIUtil.processUnSuccessResponce;

/**
 * Created by Admin on 8/28/2017.
 */

public class SettingsAPI extends APIAbstact {

    private SettingsAPI() {
        // empty method
    }

    public static void getAllCategories(final SettingsEventSubscriber subscriber) {

        sApiInterface.getCategory().enqueue(new Callback<SettingsResponse>() {

            @Override
            public void onResponse(Call<SettingsResponse> call, Response<SettingsResponse> response) {
                Log.i("TAG_IN_SETTINGSAPI", "Hiiiiiiiii");
                if (response.isSuccessful()) {
                    subscriber.onSettingsCompleted(response.body());
                } else {
                      subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<SettingsResponse> call, Throwable t) {
                  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }

    public static void getCategoryListByBrand(final SettingsEventSubscriber subscriber) {

        sApiInterface.getBrand().enqueue(new Callback<SettingsResponse>() {
            @Override
            public void onResponse(Call<SettingsResponse> call, Response<SettingsResponse> response) {
                Log.i("TAG_IN_SETTINGSAPI", "Hiiiiiiiii");

                if (response.isSuccessful()) {

                    subscriber.onSettingsCompleted(response.body());
                } else {
                      subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<SettingsResponse> call, Throwable t) {
                  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }
}

