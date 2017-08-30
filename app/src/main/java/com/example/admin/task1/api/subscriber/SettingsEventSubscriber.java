package com.example.admin.task1.api.subscriber;


import com.example.admin.task1.api.response.BrandsResponse;
import com.example.admin.task1.api.response.SettingsResponse;

/**
 * Created by Admin on 8/28/2017.
 */

public interface SettingsEventSubscriber
{
    void onSettingsCompleted(SettingsResponse settingsResponse);
    void onBrandCompleted(BrandsResponse brandsResponse);
}
