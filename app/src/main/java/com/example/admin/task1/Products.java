package com.example.admin.task1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 7/26/2017.
 */

public class Products implements Parcelable {
    private String version, mobileName, mobilePrize, mobileRating, ratingInWords;
    private int image, imgView1, imgView2, imgView3,imgView4;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.version);
        dest.writeString(this.mobileName);
        dest.writeString(this.mobilePrize);
        dest.writeString(this.mobileRating);
        dest.writeString(this.ratingInWords);
        dest.writeInt(this.image);
        dest.writeInt(this.imgView1);
        dest.writeInt(this.imgView2);
        dest.writeInt(this.imgView3);
        dest.writeInt(this.imgView4);
    }

    public Products(String version, String mobileName, String mobilePrize, String mobileRating, String ratingInWords, int image, int imgView1, int imgView2, int imgView3, int imgView4) {
        this.version = version;
        this.mobileName = mobileName;
        this.mobilePrize = mobilePrize;
        this.mobileRating = mobileRating;
        this.ratingInWords = ratingInWords;
        this.image = image;
        this.imgView1 = imgView1;
        this.imgView2 = imgView2;
        this.imgView3 = imgView3;
        this.imgView4 = imgView4;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getMobilePrize() {
        return mobilePrize;
    }

    public void setMobilePrize(String mobilePrize) {
        this.mobilePrize = mobilePrize;
    }

    public String getMobileRating() {
        return mobileRating;
    }

    public void setMobileRating(String mobileRating) {
        this.mobileRating = mobileRating;
    }

    public String getRatingInWords() {
        return ratingInWords;
    }

    public void setRatingInWords(String ratingInWords) {
        this.ratingInWords = ratingInWords;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImgView1() {
        return imgView1;
    }

    public void setImgView1(int imgView1) {
        this.imgView1 = imgView1;
    }

    public int getImgView2() {
        return imgView2;
    }

    public void setImgView2(int imgView2) {
        this.imgView2 = imgView2;
    }

    public int getImgView3() {
        return imgView3;
    }

    public void setImgView3(int imgView3) {
        this.imgView3 = imgView3;
    }

    public int getImgView4() {
        return imgView4;
    }

    public void setImgView4(int imgView4) {
        this.imgView4 = imgView4;
    }

    public Products() {
    }

    protected Products(Parcel in) {
        this.version = in.readString();
        this.mobileName = in.readString();
        this.mobilePrize = in.readString();
        this.mobileRating = in.readString();
        this.ratingInWords = in.readString();
        this.image = in.readInt();
        this.imgView1 = in.readInt();
        this.imgView2 = in.readInt();
        this.imgView3 = in.readInt();
        this.imgView4 = in.readInt();
    }

    public static final Parcelable.Creator<Products> CREATOR = new Parcelable.Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel source) {
            return new Products(source);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };
}
