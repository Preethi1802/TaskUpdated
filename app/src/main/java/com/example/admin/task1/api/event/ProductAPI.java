package com.example.admin.task1.api.event;


import com.example.admin.task1.api.response.ProductResponse;
import com.example.admin.task1.api.subscriber.ProductEventSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 8/28/2017.
 */

public class ProductAPI extends APIAbstact {
    private ProductAPI() {
        // empty method
    }


    public static void getAllProducts(final ProductEventSubscriber subscriber) {

        sApiInterface.getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onProductCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }

    public static void getProductsByBrand(final int brandId, final ProductEventSubscriber subscriber) {

        sApiInterface.getProductsByBrand(brandId).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onProductCompleted(response.body());
                } else {
                    //  subscriber.onSettingsCompleted(processUnSuccessResponce(response.code(), response.errorBody(), SettingsResponse.class));
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });

    }

    public static void getProductsByCategory(final int categoryId, final ProductEventSubscriber subscriber) {

        sApiInterface.getProductsByCategory(categoryId).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    subscriber.onProductCompleted(response.body());
                } else {
                    //   subscriber.onProductCompleted(processUnSuccessResponce(response.code(), response.errorBody(), ProductResponse.class));
                }
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                //  subscriber.onSettingsCompleted(getGenericResponseErr(SettingsResponse.class, t ));
            }
        });
    }


}
