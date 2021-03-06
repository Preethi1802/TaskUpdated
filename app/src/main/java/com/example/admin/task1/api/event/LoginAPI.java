package com.example.admin.task1.api.event;

import android.util.Log;

import com.example.admin.task1.api.request.LoginRequest;
import com.example.admin.task1.api.response.LoginResponse;
import com.example.admin.task1.api.subscriber.LoginEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.admin.task1.api.util.APIUtil.getGenericResponseErr;
import static com.example.admin.task1.api.util.APIUtil.processUnSuccessResponce;

/**
 * Created by Admin on 9/5/2017.
 */

public class LoginAPI extends APIAbstact {
    private static String TAG = "LoginAPI";

    private LoginAPI() {
        // empty method
    }

    public static void postLoginDetails(LoginRequest request,final LoginEventSubscriber subscriber) {

        sApiInterface.postLoginDetails(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onLoginCompleted(response.body());
                } else {
                      subscriber.onLoginCompleted(processUnSuccessResponce(response.code(), response.errorBody(), LoginResponse.class));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                  subscriber.onLoginCompleted(getGenericResponseErr(LoginResponse.class, t ));
            }
        });

    }


}