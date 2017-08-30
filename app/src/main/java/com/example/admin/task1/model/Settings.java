package com.example.admin.task1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 8/30/2017.
 */

public class Settings
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_shopping_enabled")
    @Expose
    private Integer isShoppingEnabled;
    @SerializedName("is_coupon_enabled")
    @Expose
    private Integer isCouponEnabled;
    @SerializedName("is_tax_enabled")
    @Expose
    private Integer isTaxEnabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsShoppingEnabled() {
        return isShoppingEnabled;
    }

    public void setIsShoppingEnabled(Integer isShoppingEnabled) {
        this.isShoppingEnabled = isShoppingEnabled;
    }

    public Integer getIsCouponEnabled() {
        return isCouponEnabled;
    }

    public void setIsCouponEnabled(Integer isCouponEnabled) {
        this.isCouponEnabled = isCouponEnabled;
    }

    public Integer getIsTaxEnabled() {
        return isTaxEnabled;
    }

    public void setIsTaxEnabled(Integer isTaxEnabled) {
        this.isTaxEnabled = isTaxEnabled;
    }

}