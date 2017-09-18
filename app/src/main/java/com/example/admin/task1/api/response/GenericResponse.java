package com.example.admin.task1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 8/30/2017.
 */

public class GenericResponse {

    @Expose
    @SerializedName("success")
    public boolean success;

    @Expose
    @SerializedName("message")
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        message = msg;
    }

    public boolean isSuccess() {
        return this.success == true;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
