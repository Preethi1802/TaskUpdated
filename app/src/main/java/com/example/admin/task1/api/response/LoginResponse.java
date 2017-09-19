package com.example.admin.task1.api.response;

import com.example.admin.task1.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/5/2017.
 */

public class LoginResponse extends GenericResponse
{
    @Expose
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
