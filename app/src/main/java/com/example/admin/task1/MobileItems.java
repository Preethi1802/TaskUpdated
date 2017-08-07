package com.example.admin.task1;

/**
 * Created by Admin on 7/26/2017.
 */

public class MobileItems {
    private String mobileName, version;
    private int Image;
    private String mobilePrize, mobileRating, ratingInWords;
    private int imgView1, imgView2, imgView3,imgView4;

    public MobileItems(int i,int imgView1,
                       int imgView2, int imgView3, int imgView4, String name, String s, String mobilePrize, String mobileRating,
                       String ratingInWords )
    {
        this.mobileName = name;
        this.version = s;
        this.Image = i;
        this.mobilePrize = mobilePrize;
        this.mobileRating = mobileRating;
        this.ratingInWords = ratingInWords;
        this.imgView1 = imgView1;
        this.imgView2 = imgView2;
        this.imgView3 = imgView3;
        this.imgView4 = imgView4;

    }


    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image)
    {
        this.Image = image;
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
}
