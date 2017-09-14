package com.example.admin.task1.api.subscriber;

import com.example.admin.task1.api.response.CartResponse;

/**
 * Created by Admin on 9/14/2017.
 */

public interface CartEventSubscriber
{
    void onCartCompleted(CartResponse cartResponse);
}
