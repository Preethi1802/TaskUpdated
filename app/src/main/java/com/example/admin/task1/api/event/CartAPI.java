package com.example.admin.task1.api.event;

import android.util.Log;

import com.example.admin.task1.api.request.CartRequest;
import com.example.admin.task1.api.response.CartResponse;
import com.example.admin.task1.api.subscriber.CartEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 9/14/2017.
 */

public class CartAPI extends APIAbstact
{
    private static String TAG = "FirbaseLoginAPI";

    private CartAPI() {
        // empty method
    }
    public static void getCart(int userId, final CartEventSubscriber subscriber) {

        sApiInterface.getCart(userId).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onCartCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }

    public static void postAddCart(CartRequest request, final CartEventSubscriber subscriber) {

        sApiInterface.postAddCart(request).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onCartCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }

    public static void postRemoveCart(CartRequest request, final CartEventSubscriber subscriber) {

        sApiInterface.postRemoveCart(request).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onCartCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }
}
