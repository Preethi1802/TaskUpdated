package com.example.admin.task1.api.util;

import android.app.Activity;

import com.example.admin.task1.api.event.CartAPI;
import com.example.admin.task1.api.event.FirbaseLoginAPI;
import com.example.admin.task1.api.event.LoginAPI;
import com.example.admin.task1.api.event.ProductAPI;
import com.example.admin.task1.api.event.RegistrationAPI;
import com.example.admin.task1.api.event.SettingsAPI;
import com.example.admin.task1.api.request.CartRequest;
import com.example.admin.task1.api.request.FirebaseLoginRequest;
import com.example.admin.task1.api.request.LoginRequest;
import com.example.admin.task1.api.request.RegistrationRequest;
import com.example.admin.task1.api.subscriber.CartEventSubscriber;
import com.example.admin.task1.api.subscriber.FirebaseLoginEventSubscriber;
import com.example.admin.task1.api.subscriber.LoginEventSubscriber;
import com.example.admin.task1.api.subscriber.ProductEventSubscriber;
import com.example.admin.task1.api.subscriber.RegistrationEventSubscriber;
import com.example.admin.task1.api.subscriber.SettingsEventSubscriber;

/**
 * Created by Admin on 9/4/2017.
 */

public class CommunicationManager {
    private static final CommunicationManager ourInstance = new CommunicationManager();

    public static CommunicationManager getInstance() {
        return ourInstance;
    }

    private CommunicationManager() {
    }

    public void getAllProducts(Activity activity, int page){
        ProductAPI.getAllProducts(page,(ProductEventSubscriber) activity);
    }
    public void getProductsByBrand(Activity activity, int brandId){
        ProductAPI.getProductsByBrand(brandId,(ProductEventSubscriber) activity);
    }
    public void getProductsByCategory(Activity activity,int categoryId){
        ProductAPI.getProductsByCategory(categoryId,(ProductEventSubscriber) activity);
    }
    public void getAllCategories(Activity activity){
        SettingsAPI.getAllCategories((SettingsEventSubscriber) activity);
    }
    public void getCategoryListByBrand(Activity activity){
        SettingsAPI.getCategoryListByBrand((SettingsEventSubscriber) activity);
    }
    public void postRegistrationDetails(RegistrationRequest request, Activity activity){
        RegistrationAPI.postRegistrationDetails(request, (RegistrationEventSubscriber) activity);
    }
    public void postLoginDetails( LoginRequest request,Activity activity){
        LoginAPI.postLoginDetails(request,(LoginEventSubscriber) activity);
    }

    public void postFirebaseLoginDetails(FirebaseLoginRequest request, Activity activity){
        FirbaseLoginAPI.postFirbaseLoginDetails(request,(FirebaseLoginEventSubscriber) activity);
    }
    public void getCart( Activity activity, int userId){
        CartAPI.getCart(userId, (CartEventSubscriber) activity);
    }
    public void postAddCart(CartRequest request, Activity activity){
        CartAPI.postAddCart(request,(CartEventSubscriber) activity);
    }
    public void postRemoveCart(CartRequest request, Activity activity){
        CartAPI.postRemoveCart(request,(CartEventSubscriber) activity);
    }
}
