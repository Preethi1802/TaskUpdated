package com.example.admin.task1.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/5/2017.
 */

public class RegistrationRequest {

    @Expose
    @SerializedName("email")
    public String email;

    @Expose
    @SerializedName("mobile_number")
    public String mobileNumber;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("password")
    public String password;

    @Expose
    @SerializedName("password_confirmation")
    public String confrimPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return confrimPassword;
    }

    public void setPassword_confirmation(String confrimPassword) {
        this.confrimPassword = confrimPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
