package com.example.admin.task1.api.subscriber;

import com.example.admin.task1.api.response.LoginResponse;

/**
 * Created by Admin on 9/5/2017.
 */

public interface LoginEventSubscriber
{
    void onLoginCompleted(LoginResponse loginResponse);
}
