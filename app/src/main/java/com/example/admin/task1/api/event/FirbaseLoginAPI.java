package com.example.admin.task1.api.event;

import android.util.Log;

import com.example.admin.task1.api.request.FirebaseLoginRequest;
import com.example.admin.task1.api.response.FirebaseLoginResponse;
import com.example.admin.task1.api.subscriber.FirebaseLoginEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 9/13/2017.
 */

public class FirbaseLoginAPI extends APIAbstact
{
    private static String TAG = "FirbaseLoginAPI";

    private FirbaseLoginAPI() {
        // empty method
    }

    public static void postFirbaseLoginDetails(FirebaseLoginRequest request, final FirebaseLoginEventSubscriber subscriber) {

        sApiInterface.postFirebaseLoginDetails(request).enqueue(new Callback<FirebaseLoginResponse>() {
            @Override
            public void onResponse(Call<FirebaseLoginResponse> call, Response<FirebaseLoginResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onFirebaseLoginCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<FirebaseLoginResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }
}
