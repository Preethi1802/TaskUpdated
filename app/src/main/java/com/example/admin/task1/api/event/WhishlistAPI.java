package com.example.admin.task1.api.event;

import android.util.Log;

import com.example.admin.task1.api.request.WishlistRequest;
import com.example.admin.task1.api.response.AddWishListResponse;
import com.example.admin.task1.api.response.GetWishListResponse;
import com.example.admin.task1.api.response.RemoveWishListResponse;
import com.example.admin.task1.api.subscriber.WishlistProductEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 9/15/2017.
 */

public class WhishlistAPI extends APIAbstact {

    private static String TAG = "WhishlistAPI";

    private WhishlistAPI() {
        // empty method
    }

    public static void getWhishlistProducts(int userId, final WishlistProductEventSubscriber subscriber) {

        sApiInterface.getWhishlistProducts(userId).enqueue(new Callback<GetWishListResponse>() {
            @Override
            public void onResponse(Call<GetWishListResponse> call, Response<GetWishListResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onGetWhishlistCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }
            @Override
            public void onFailure(Call<GetWishListResponse> call, Throwable t) {
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }

    public static void postAddProductsToWhishlist(WishlistRequest request, final WishlistProductEventSubscriber subscriber) {

        sApiInterface.postAddProductsToWhishlist(request).enqueue(new Callback<AddWishListResponse>() {
            @Override
            public void onResponse(Call<AddWishListResponse> call, Response<AddWishListResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onAddWishListCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<AddWishListResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }

    public static void postRemoveFromWhishlist(WishlistRequest request, final WishlistProductEventSubscriber subscriber) {

        sApiInterface.postRemoveFromWhishlist(request).enqueue(new Callback<RemoveWishListResponse>() {
            @Override
            public void onResponse(Call<RemoveWishListResponse> call, Response<RemoveWishListResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onRemoveWishListCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<RemoveWishListResponse> call, Throwable t) {
                Log.getStackTraceString(t);
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }
}
