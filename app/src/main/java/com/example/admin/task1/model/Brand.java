package com.example.admin.task1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 8/29/2017.
 */

public class Brand implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon_name")
    @Expose
    private Object iconName;
    @SerializedName("icon_path")
    @Expose
    private Object iconPath;
    @SerializedName("image_name")
    @Expose
    private Object imageName;
    @SerializedName("image_path")
    @Expose
    private Object imagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getIconName() {
        return iconName;
    }

    public void setIconName(Object iconName) {
        this.iconName = iconName;
    }

    public Object getIconPath() {
        return iconPath;
    }

    public void setIconPath(Object iconPath) {
        this.iconPath = iconPath;
    }

    public Object getImageName() {
        return imageName;
    }

    public void setImageName(Object imageName) {
        this.imageName = imageName;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeParcelable((Parcelable) this.iconName, flags);
        dest.writeParcelable((Parcelable) this.iconPath, flags);
        dest.writeParcelable((Parcelable) this.imageName, flags);
        dest.writeParcelable((Parcelable) this.imagePath, flags);
    }

    public Brand() {
    }

    protected Brand(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.iconName = in.readParcelable(Object.class.getClassLoader());
        this.iconPath = in.readParcelable(Object.class.getClassLoader());
        this.imageName = in.readParcelable(Object.class.getClassLoader());
        this.imagePath = in.readParcelable(Object.class.getClassLoader());
    }

    public static final Parcelable.Creator<Brand> CREATOR = new Parcelable.Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel source) {
            return new Brand(source);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };
}