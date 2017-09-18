package com.example.admin.task1.api.event;

import android.util.Log;

import com.example.admin.task1.api.request.CartRequest;
import com.example.admin.task1.api.response.AddCartResponse;
import com.example.admin.task1.api.response.GetCartResponse;
import com.example.admin.task1.api.response.RemoveCartResponse;
import com.example.admin.task1.api.subscriber.CartEventSubscriber;
import com.thapovan.android.commonutils.log.L;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.admin.task1.api.util.APIUtil.getGenericResponseErr;
import static com.example.admin.task1.api.util.APIUtil.processUnSuccessResponce;

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

        sApiInterface.getCart(userId).enqueue(new Callback<GetCartResponse>() {
            @Override
            public void onResponse(Call<GetCartResponse> call, Response<GetCartResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onGetCartCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<GetCartResponse> call, Throwable t) {
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }

    public static void postAddCart(CartRequest request, final CartEventSubscriber subscriber) {

        sApiInterface.postAddCart(request).enqueue(new Callback<AddCartResponse>() {
            @Override
            public void onResponse(Call<AddCartResponse> call, Response<AddCartResponse> response) {
                if (response.isSuccessful()) {
                    L.d("### 1");
                    subscriber.onAddCartCompleted(response.body());
                } else {
                    L.d("### 2");
                    subscriber.onAddCartCompleted(processUnSuccessResponce(response.code(), response.errorBody(), AddCartResponse.class));
                }
            }

            @Override
            public void onFailure(Call<AddCartResponse> call, Throwable t) {
                L.d("### 3");
                Log.getStackTraceString(t);
                subscriber.onAddCartCompleted(getGenericResponseErr(AddCartResponse.class, t ));
            }
        });

    }

    public static void postRemoveCart(CartRequest request, final CartEventSubscriber subscriber) {

        sApiInterface.postRemoveCart(request).enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onRemoveCartCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }
}
