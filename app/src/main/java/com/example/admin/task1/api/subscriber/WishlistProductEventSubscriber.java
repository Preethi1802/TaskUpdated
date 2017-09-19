package com.example.admin.task1.api.subscriber;

import com.example.admin.task1.api.response.WishListPostAddResponse;
import com.example.admin.task1.api.response.WishListGetResponse;
import com.example.admin.task1.api.response.WishListPostRemoveResponse;

/**
 * Created by Admin on 9/15/2017.
 */

public interface WishlistProductEventSubscriber
{
    void onGetWhishlistCompleted(WishListGetResponse wishListGetResponse);
    void onAddWishListCompleted(WishListPostAddResponse wishListPostAddResponse);
    void onRemoveWishListCompleted(WishListPostRemoveResponse wishListPostRemoveResponse);
}
