package com.example.admin.task1.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/13/2017.
 */

public class FirebaseLoginRequest
{
    @Expose
    @SerializedName("email")
    public String email;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("provider")
    public String provider;

    @Expose
    @SerializedName("provider_id")
    public String provider_id;

    @Expose
    @SerializedName("uid")
    public String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }
}
