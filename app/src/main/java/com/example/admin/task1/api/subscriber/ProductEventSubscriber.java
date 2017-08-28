package com.example.admin.task1.api.subscriber;


import com.example.admin.task1.api.response.ProductResponse;

/**
 * Created by Admin on 8/28/2017.
 */

public interface ProductEventSubscriber
{
    void onProductCompleted(ProductResponse productResponse);
}
