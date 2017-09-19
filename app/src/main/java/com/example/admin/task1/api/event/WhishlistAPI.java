package com.example.admin.task1.api.event;

import android.util.Log;

import com.example.admin.task1.api.request.WishlistRequest;
import com.example.admin.task1.api.response.WishListPostAddResponse;
import com.example.admin.task1.api.response.WishListGetResponse;
import com.example.admin.task1.api.response.WishListPostRemoveResponse;
import com.example.admin.task1.api.subscriber.WishlistProductEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.admin.task1.api.util.APIUtil.getGenericResponseErr;
import static com.example.admin.task1.api.util.APIUtil.processUnSuccessResponce;

/**
 * Created by Admin on 9/15/2017.
 */

public class WhishlistAPI extends APIAbstact {

    private static String TAG = "WhishlistAPI";

    private WhishlistAPI() {
        // empty method
    }

    public static void getWhishlistProducts(int userId, final WishlistProductEventSubscriber subscriber) {

        sApiInterface.getWhishlistProducts(userId).enqueue(new Callback<WishListGetResponse>() {
            @Override
            public void onResponse(Call<WishListGetResponse> call, Response<WishListGetResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onGetWhishlistCompleted(response.body());
                } else {
                      subscriber.onGetWhishlistCompleted(processUnSuccessResponce(response.code(), response.errorBody(), WishListGetResponse.class));
                }
            }
            @Override
            public void onFailure(Call<WishListGetResponse> call, Throwable t) {
                  subscriber.onGetWhishlistCompleted(getGenericResponseErr(WishListGetResponse.class, t ));
            }
        });
    }

    public static void postAddProductsToWhishlist(WishlistRequest request, final WishlistProductEventSubscriber subscriber) {

        sApiInterface.postAddProductsToWhishlist(request).enqueue(new Callback<WishListPostAddResponse>() {
            @Override
            public void onResponse(Call<WishListPostAddResponse> call, Response<WishListPostAddResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onAddWishListCompleted(response.body());
                } else {
                      subscriber.onAddWishListCompleted(processUnSuccessResponce(response.code(), response.errorBody(), WishListPostAddResponse.class));
                }
            }

            @Override
            public void onFailure(Call<WishListPostAddResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                  subscriber.onAddWishListCompleted(getGenericResponseErr(WishListPostAddResponse.class, t ));
            }
        });
    }

    public static void postRemoveFromWhishlist(WishlistRequest request, final WishlistProductEventSubscriber subscriber) {

        sApiInterface.postRemoveFromWhishlist(request).enqueue(new Callback<WishListPostRemoveResponse>() {
            @Override
            public void onResponse(Call<WishListPostRemoveResponse> call, Response<WishListPostRemoveResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onRemoveWishListCompleted(response.body());
                } else {
                      subscriber.onRemoveWishListCompleted(processUnSuccessResponce(response.code(), response.errorBody(), WishListPostRemoveResponse.class));
                }
            }

            @Override
            public void onFailure(Call<WishListPostRemoveResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                  subscriber.onRemoveWishListCompleted(getGenericResponseErr(WishListPostRemoveResponse.class, t ));
            }
        });
    }
}
