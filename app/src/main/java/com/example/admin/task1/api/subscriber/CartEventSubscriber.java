package com.example.admin.task1.api.subscriber;

import com.example.admin.task1.api.response.AddCartResponse;
import com.example.admin.task1.api.response.GetCartResponse;
import com.example.admin.task1.api.response.RemoveCartResponse;

/**
 * Created by Admin on 9/14/2017.
 */

public interface CartEventSubscriber
{
    void onGetCartCompleted(GetCartResponse getCartResponse);
    void onAddCartCompleted(AddCartResponse addCartResponse);
    void onRemoveCartCompleted(RemoveCartResponse removeCartResponse);

}
