package com.example.admin.task1.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 7/26/2017.
 */

public class Product implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("spec")
    @Expose
    private String spec;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("regular_price")
    @Expose
    private String regularPrice;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("shipping_price")
    @Expose
    private String shippingPrice;
    @SerializedName("delivery_days")
    @Expose
    private String deliveryDays;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("is_in_stock")
    @Expose
    private Integer isInStock;
    @SerializedName("is_taxable")
    @Expose
    private Integer isTaxable;
    @SerializedName("is_featured")
    @Expose
    private Integer isFeatured;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("images")
    @Expose
    private List<ImageItem> images = new ArrayList<>();

    public Product(Integer id, String name, String description, String spec, Integer quantity, String sku, String regularPrice, String salePrice, String shippingPrice, String deliveryDays, Integer status, Integer isInStock, Integer isTaxable, Integer isFeatured, String createdAt, String updatedAt, Object deletedAt, List<ImageItem> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.spec = spec;
        this.quantity = quantity;
        this.sku = sku;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.shippingPrice = shippingPrice;
        this.deliveryDays = deliveryDays;
        this.status = status;
        this.isInStock = isInStock;
        this.isTaxable = isTaxable;
        this.isFeatured = isFeatured;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.images = images;
    }

    public int getId() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(String shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public String getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(String deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(Integer isInStock) {
        this.isInStock = isInStock;
    }

    public Integer getIsTaxable() {
        return isTaxable;
    }

    public void setIsTaxable(Integer isTaxable) {
        this.isTaxable = isTaxable;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
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

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public ImageItem getFeaturedImages() {
        for (int i = 0; i < images.size(); i++) {

            if (images.get(i).getType().equals("FEATURED")) {

                return images.get(i);
            }
        }

        return null;
    }

    public ArrayList<ImageItem> getGalleryImages() {
        ArrayList<ImageItem> imageItems = new ArrayList();
        int i;
        ImageItem j = new ImageItem();
        for (i = 0; i < images.size(); i++) {
            if (images.get(i).getType().equals("GALLERY")) {

                Log.i("Products", "" + images.get(i));
                imageItems.add(images.get(i));
            }
        }
        Log.i("Products", "" + imageItems.size());

        return imageItems;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.spec);
        dest.writeValue(this.quantity);
        dest.writeString(this.sku);
        dest.writeString(this.regularPrice);
        dest.writeString(this.salePrice);
        dest.writeString(this.shippingPrice);
        dest.writeString(this.deliveryDays);
        dest.writeValue(this.status);
        dest.writeValue(this.isInStock);
        dest.writeValue(this.isTaxable);
        dest.writeValue(this.isFeatured);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeTypedList(this.images);
    }

    protected Product(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.spec = in.readString();
        this.quantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sku = in.readString();
        this.regularPrice = in.readString();
        this.salePrice = in.readString();
        this.shippingPrice = in.readString();
        this.deliveryDays = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isInStock = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isTaxable = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isFeatured = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.images = in.createTypedArrayList(ImageItem.CREATOR);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };






























   /* private String spec, name, regular_price, mobileRating, description;
    private int image, imgView1, imgView2, imgView3, imgView4;
    private String URL ,urlView1, urlView2, urlView3, urlView4;
    ArrayList<ImageItem> imageItems;

    public Product(ArrayList<ImageItem> imageItems) {
        this.imageItems = imageItems;
    }


    public ArrayList<ImageItem> getImageItems() {
        return imageItems;
    }

    public void setImageItems(ArrayList<ImageItem> imageItems) {
        this.imageItems = imageItems;
    }

    public Product() {

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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getMobileRating() {
        return mobileRating;
    }

    public void setMobileRating(String mobileRating) {
        this.mobileRating = mobileRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public static Creator<Product> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.spec);
        dest.writeString(this.name);
        dest.writeString(this.regular_price);
        dest.writeString(this.mobileRating);
        dest.writeString(this.description);
        dest.writeInt(this.image);
        dest.writeInt(this.imgView1);
        dest.writeInt(this.imgView2);
        dest.writeInt(this.imgView3);
        dest.writeInt(this.imgView4);
        dest.writeString(this.URL);
        dest.writeString(this.urlView1);
        dest.writeString(this.urlView2);
        dest.writeString(this.urlView3);
        dest.writeString(this.urlView4);
    }

    protected Product(Parcel in) {
        this.spec = in.readString();
        this.name = in.readString();
        this.regular_price = in.readString();
        this.mobileRating = in.readString();
        this.description = in.readString();
        this.image = in.readInt();
        this.imgView1 = in.readInt();
        this.imgView2 = in.readInt();
        this.imgView3 = in.readInt();
        this.imgView4 = in.readInt();
        this.URL = in.readString();
        this.urlView1 = in.readString();
        this.urlView2 = in.readString();
        this.urlView3 = in.readString();
        this.urlView4 = in.readString();

    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };*/


}
