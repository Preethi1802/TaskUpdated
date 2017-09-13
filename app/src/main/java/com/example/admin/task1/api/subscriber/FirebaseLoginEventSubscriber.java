package com.example.admin.task1.api.subscriber;

import com.example.admin.task1.api.response.FirebaseLoginResponse;

/**
 * Created by Admin on 9/13/2017.
 */

public interface FirebaseLoginEventSubscriber
{
    void onFirebaseLoginCompleted(FirebaseLoginResponse firebaseLoginResponse);
}
