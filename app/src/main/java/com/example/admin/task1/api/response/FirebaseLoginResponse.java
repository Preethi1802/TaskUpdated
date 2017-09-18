package com.example.admin.task1.api.response;

import com.example.admin.task1.model.UserFirebase;

/**
 * Created by Admin on 9/13/2017.
 */

public class FirebaseLoginResponse extends GenericResponse
{

    private UserFirebase user;

    public UserFirebase getUser() {
        return user;
    }

    public void setUser(UserFirebase user) {
        this.user = user;
    }

}
