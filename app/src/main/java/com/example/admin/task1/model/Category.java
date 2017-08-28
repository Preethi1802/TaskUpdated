package com.example.admin.task1.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.admin.task1.api.remote.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 8/23/2017.
 */

public class Category implements Parcelable{
    private static final String TAG = "Category";


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parent_id")
    @Expose
    private Object parentId;
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
    @SerializedName("children")
    @Expose
    private List<Child> children = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        Log.i(TAG,"HIIIIIII");
        Log.i(TAG,")))))))))"+name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
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

    public String getIconUrl() {

        return Constants.IMAGE_URL+this.iconPath+"/"+this.iconName;
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

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeParcelable((Parcelable) this.parentId, flags);
        dest.writeParcelable((Parcelable) this.iconName, flags);
        dest.writeParcelable((Parcelable) this.iconPath, flags);
        dest.writeParcelable((Parcelable) this.imageName, flags);
        dest.writeParcelable((Parcelable) this.imagePath, flags);
        dest.writeTypedList(this.children);
    }

    public Category() {
    }

    protected Category(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.parentId = in.readParcelable(Object.class.getClassLoader());
        this.iconName = in.readParcelable(Object.class.getClassLoader());
        this.iconPath = in.readParcelable(Object.class.getClassLoader());
        this.imageName = in.readParcelable(Object.class.getClassLoader());
        this.imagePath = in.readParcelable(Object.class.getClassLoader());
        this.children = in.createTypedArrayList(Child.CREATOR);
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
