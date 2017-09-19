package com.example.admin.task1.api.subscriber;

import com.example.admin.task1.api.response.CartPostAddResponse;
import com.example.admin.task1.api.response.CartGetResponse;
import com.example.admin.task1.api.response.CartPostRemoveResponse;

/**
 * Created by Admin on 9/14/2017.
 */

public interface CartEventSubscriber
{
    void onGetCartCompleted(CartGetResponse cartGetResponse);
    void onAddCartCompleted(CartPostAddResponse cartPostAddResponse);
    void onRemoveCartCompleted(CartPostRemoveResponse cartPostRemoveResponse);

}
