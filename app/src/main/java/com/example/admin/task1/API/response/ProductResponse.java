package com.example.admin.task1.api.response;

import com.example.admin.task1.model.Product;

import java.util.List;

/**
 * Created by Admin on 8/28/2017.
 */

public class ProductResponse {


    /**
     * success : true
     * message : Products List!!
     * products : [{"id":11,"name":"Dell Laptop","description":"","spec":"","quantity":30,"sku":"","regular_price":"39999.00","sale_price":"39999.00","shipping_price":"100.00","delivery_days":"","status":1,"is_in_stock":1,"is_taxable":1,"is_featured":1,"images":[{"id":60,"product_id":11,"name":"1503060104_71rqUkQnZeL._SL1500_.jpg","path":"storage/uploads/images/products","type":"FEATURED"},{"id":61,"product_id":11,"name":"1503060164_6219363_7333d0b59cf4e1dad518905cfef619d4.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":62,"product_id":11,"name":"1503060164_Dell_Inspiron_3558_4_0.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":63,"product_id":11,"name":"1503060164_dell_i3567_5820blk_inspiron_pro_i5_7200u_1346925.jpg","path":"storage/uploads/images/products","type":"GALLERY"}]},{"id":10,"name":"I-Phone","description":"","spec":"","quantity":150,"sku":"","regular_price":"50000.00","sale_price":"50000.00","shipping_price":"100.00","delivery_days":"","status":1,"is_in_stock":1,"is_taxable":1,"is_featured":1,"images":[{"id":47,"product_id":10,"name":"1503059630_iphone.jpg","path":"storage/uploads/images/products","type":"FEATURED"},{"id":48,"product_id":10,"name":"1503059671_iphoneback.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":49,"product_id":10,"name":"1503059671_iphoneside.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":50,"product_id":10,"name":"1503059671_iphoneview.jpg","path":"storage/uploads/images/products","type":"GALLERY"}]},{"id":9,"name":"Samsung Galaxy","description":"","spec":"","quantity":68,"sku":"","regular_price":"34000.00","sale_price":"34000.00","shipping_price":"80.00","delivery_days":"","status":1,"is_in_stock":1,"is_taxable":1,"is_featured":1,"images":[{"id":46,"product_id":9,"name":"1503059444_sgalaxy.jpg","path":"storage/uploads/images/products","type":"FEATURED"},{"id":54,"product_id":9,"name":"1503059776_galaxyback.png","path":"storage/uploads/images/products","type":"GALLERY"},{"id":55,"product_id":9,"name":"1503059776_galaxyside.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":56,"product_id":9,"name":"1503059776_galaxyview.jpg","path":"storage/uploads/images/products","type":"GALLERY"}]},{"id":8,"name":"Lenovo","description":"","spec":"","quantity":45,"sku":"","regular_price":"13000.00","sale_price":"13000.00","shipping_price":"50.00","delivery_days":"","status":1,"is_in_stock":1,"is_taxable":1,"is_featured":1,"images":[{"id":42,"product_id":8,"name":"1503059368_lenovo.jpg","path":"storage/uploads/images/products","type":"FEATURED"},{"id":51,"product_id":8,"name":"1503059763_lenovoback.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":52,"product_id":8,"name":"1503059764_lenovoside.jpg","path":"storage/uploads/images/products","type":"GALLERY"},{"id":53,"product_id":8,"name":"1503059764_lenovoview.jpeg","path":"storage/uploads/images/products","type":"GALLERY"}]},{"id":1,"name":"Moto E","description":"<p><\/p><pre><br><\/pre><p><\/p>","spec":"<p><\/p><pre><br><\/pre><p><\/p>","quantity":55,"sku":"","regular_price":"13000.00","sale_price":"13000.00","shipping_price":"100.00","delivery_days":"","status":1,"is_in_stock":1,"is_taxable":0,"is_featured":0,"images":[{"id":1,"product_id":1,"name":"1502877986_motoo.jpeg","path":"storage/uploads/images/products","type":"FEATURED"},{"id":64,"product_id":1,"name":"1503298579_motoback.png","path":"storage/uploads/images/products","type":"GALLERY"},{"id":65,"product_id":1,"name":"1503298579_motoside.png","path":"storage/uploads/images/products","type":"GALLERY"},{"id":66,"product_id":1,"name":"1503298580_motoview.jpg","path":"storage/uploads/images/products","type":"GALLERY"}]}]
     */

    private boolean success;
    private String message;
    private List<Product> products;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
