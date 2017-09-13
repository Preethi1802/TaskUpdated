package com.example.admin.task1.api.response;

import com.example.admin.task1.model.UserFirebase;

/**
 * Created by Admin on 9/13/2017.
 */

public class FirebaseLoginResponse
{

    /**
     * success : true
     * message : Login Success...
     * user : {"id":2,"name":"a","email":"a@gmail.com","mobile_number":"9999999999"}
     */

    private boolean success;
    private String message;
    private UserFirebase user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserFirebase getUser() {
        return user;
    }

    public void setUser(UserFirebase user) {
        this.user = user;
    }

}
