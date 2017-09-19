package com.example.admin.task1.api.event;

import android.util.Log;

import com.example.admin.task1.api.request.CartRequest;
import com.example.admin.task1.api.response.CartPostAddResponse;
import com.example.admin.task1.api.response.CartGetResponse;
import com.example.admin.task1.api.response.CartPostRemoveResponse;
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

        sApiInterface.getCart(userId).enqueue(new Callback<CartGetResponse>() {
            @Override
            public void onResponse(Call<CartGetResponse> call, Response<CartGetResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onGetCartCompleted(response.body());
                } else {
                      subscriber.onGetCartCompleted(processUnSuccessResponce(response.code(), response.errorBody(), CartGetResponse.class));
                }
            }

            @Override
            public void onFailure(Call<CartGetResponse> call, Throwable t) {
                  subscriber.onGetCartCompleted(getGenericResponseErr(CartGetResponse.class, t ));
            }
        });

    }

    public static void postAddCart(CartRequest request, final CartEventSubscriber subscriber) {

        sApiInterface.postAddCart(request).enqueue(new Callback<CartPostAddResponse>() {
            @Override
            public void onResponse(Call<CartPostAddResponse> call, Response<CartPostAddResponse> response) {
                if (response.isSuccessful()) {
                    L.d("### 1");
                    subscriber.onAddCartCompleted(response.body());
                } else {
                    L.d("### 2");
                    subscriber.onAddCartCompleted(processUnSuccessResponce(response.code(), response.errorBody(), CartPostAddResponse.class));
                }
            }

            @Override
            public void onFailure(Call<CartPostAddResponse> call, Throwable t) {
                L.d("### 3");
                Log.getStackTraceString(t);
                subscriber.onAddCartCompleted(getGenericResponseErr(CartPostAddResponse.class, t ));
            }
        });

    }

    public static void postRemoveCart(CartRequest request, final CartEventSubscriber subscriber) {

        sApiInterface.postRemoveCart(request).enqueue(new Callback<CartPostRemoveResponse>() {
            @Override
            public void onResponse(Call<CartPostRemoveResponse> call, Response<CartPostRemoveResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onRemoveCartCompleted(response.body());
                } else {
                      subscriber.onRemoveCartCompleted(processUnSuccessResponce(response.code(), response.errorBody(), CartPostRemoveResponse.class));
                }
            }

            @Override
            public void onFailure(Call<CartPostRemoveResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                  subscriber.onRemoveCartCompleted(getGenericResponseErr(CartPostRemoveResponse.class, t ));
            }
        });

    }
}
