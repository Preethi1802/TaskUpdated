package com.example.admin.task1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.admin.task1.api.util.APIUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 8/17/2017.
 */

public class ImageItem implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public String getFeaturedImageURL() {

        return APIUtil.IMAGE_URL + this.path + "/" + this.name;
    }

    public String getGalleryImageURL() {

        return APIUtil.IMAGE_URL + this.path + "/" + this.name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.productId);
        dest.writeString(this.name);
        dest.writeString(this.path);
        dest.writeString(this.type);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public ImageItem() {
    }

    protected ImageItem(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.productId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.path = in.readString();
        this.type = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<ImageItem> CREATOR = new Parcelable.Creator<ImageItem>() {
        @Override
        public ImageItem createFromParcel(Parcel source) {
            return new ImageItem(source);
        }

        @Override
        public ImageItem[] newArray(int size) {
            return new ImageItem[size];
        }
    };
}
