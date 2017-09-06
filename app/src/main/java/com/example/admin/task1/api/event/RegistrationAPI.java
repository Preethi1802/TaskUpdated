package com.example.admin.task1.api.event;

import com.example.admin.task1.api.request.RegistrationRequest;
import com.example.admin.task1.api.response.RegistrationResponse;
import com.example.admin.task1.api.subscriber.RegistrationEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 9/5/2017.
 */

public class RegistrationAPI extends APIAbstact {
    private static String TAG = "RegistrationAPI";
    private RegistrationAPI() {
        // empty method
    }

    public static void postRegistrationDetails(RegistrationRequest request, final RegistrationEventSubscriber subscriber) {

        sApiInterface.postRegistrationDetails(request).enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful()) {

                    subscriber.onRegistrationCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }



}