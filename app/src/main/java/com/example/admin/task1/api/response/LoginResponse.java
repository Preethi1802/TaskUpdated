package com.example.admin.task1.api.response;

import com.example.admin.task1.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/5/2017.
 */

public class LoginResponse
{
    /**
     * success : true
     * message : Login Success
     * user : {"id":1,"name":"Karthick","email":"karthi.vels@gmail.com","mobile_number":"9952188185"}
     */
    @Expose
    @SerializedName("success")
    private boolean success;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("user")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
