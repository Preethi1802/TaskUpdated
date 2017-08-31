package com.example.admin.task1.api.subscriber;

import com.example.admin.task1.api.response.SettingsResponse;

/**
 * Created by Admin on 8/31/2017.
 */

public interface SettingsEventSubscriber {
    void onSettingsCompleted(SettingsResponse settingsResponse);
}
