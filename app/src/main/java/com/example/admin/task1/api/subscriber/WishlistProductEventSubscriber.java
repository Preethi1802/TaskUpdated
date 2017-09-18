package com.example.admin.task1.api.subscriber;

import com.example.admin.task1.api.response.AddWishListResponse;
import com.example.admin.task1.api.response.GetWishListResponse;
import com.example.admin.task1.api.response.RemoveWishListResponse;

/**
 * Created by Admin on 9/15/2017.
 */

public interface WishlistProductEventSubscriber
{
    void onGetWhishlistCompleted(GetWishListResponse getWishListResponse);
    void onAddWishListCompleted(AddWishListResponse addWishListResponse);
    void onRemoveWishListCompleted(RemoveWishListResponse removeWishListResponse);
}
