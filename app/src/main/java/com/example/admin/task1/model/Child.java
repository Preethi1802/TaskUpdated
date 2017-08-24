package com.example.admin.task1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 8/23/2017.
 */

public class Child implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
        dest.writeValue(this.parentId);
        dest.writeParcelable((Parcelable) this.iconName, flags);
        dest.writeParcelable((Parcelable) this.iconPath, flags);
        dest.writeParcelable((Parcelable) this.imageName, flags);
        dest.writeParcelable((Parcelable) this.imagePath, flags);
    }

    public Child() {
    }

    protected Child(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.parentId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.iconName = in.readParcelable(Object.class.getClassLoader());
        this.iconPath = in.readParcelable(Object.class.getClassLoader());
        this.imageName = in.readParcelable(Object.class.getClassLoader());
        this.imagePath = in.readParcelable(Object.class.getClassLoader());
    }

    public static final Parcelable.Creator<Child> CREATOR = new Parcelable.Creator<Child>() {
        @Override
        public Child createFromParcel(Parcel source) {
            return new Child(source);
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };
}