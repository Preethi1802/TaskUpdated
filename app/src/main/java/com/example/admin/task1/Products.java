package com.example.admin.task1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 7/26/2017.
 */

public class Products implements Parcelable {
    private String version, mobileName, mobilePrize, mobileRating, ratingInWords;
    private int image, imgView1, imgView2, imgView3, imgView4;
    private String URL ,urlView1, urlView2, urlView3, urlView4;

    public Products() {

    }

    public String getUrlView1() {
        return urlView1;
    }

    public void setUrlView1(String urlView1) {
        this.urlView1 = urlView1;
    }

    public String getUrlView2() {
        return urlView2;
    }

    public void setUrlView2(String urlView2) {
        this.urlView2 = urlView2;
    }

    public String getUrlView3() {
        return urlView3;
    }

    public void setUrlView3(String urlView3) {
        this.urlView3 = urlView3;
    }

    public String getUrlView4() {
        return urlView4;
    }

    public void setUrlView4(String urlView4) {
        this.urlView4 = urlView4;
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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public static Creator<Products> getCREATOR() {
        return CREATOR;
    }

    protected Products(Parcel in) {
        version = in.readString();
        mobileName = in.readString();
        mobilePrize = in.readString();
        mobileRating = in.readString();
        ratingInWords = in.readString();
        image = in.readInt();
        imgView1 = in.readInt();
        imgView2 = in.readInt();
        imgView3 = in.readInt();
        imgView4 = in.readInt();
        URL = in.readString();
        urlView1 =in.readString();
        urlView2 =in.readString();
        urlView3 =in.readString();
        urlView4 =in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(version);
        dest.writeString(mobileName);
        dest.writeString(mobilePrize);
        dest.writeString(mobileRating);
        dest.writeString(ratingInWords);
        dest.writeInt(image);
        dest.writeInt(imgView1);
        dest.writeInt(imgView2);
        dest.writeInt(imgView3);
        dest.writeInt(imgView4);
        dest.writeString(URL);
        dest.writeString(urlView1);
        dest.writeString(urlView2);
        dest.writeString(urlView3);
        dest.writeString(urlView4);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };
}
