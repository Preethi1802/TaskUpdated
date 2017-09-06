package com.example.admin.task1.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.task1.R;
import com.example.admin.task1.api.response.ProductResponse;
import com.example.admin.task1.api.subscriber.ProductEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.Brand;
import com.example.admin.task1.model.Product;
import com.example.admin.task1.product.adapter.AdapterListProduct;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 7/26/2017.
 */

public class ProductActivity extends AppActivity implements ProductEventSubscriber {
    Toolbar toolbar;
    ProductActivity mActivity;

    private static final String TAG = "ProductActivity";

    RecyclerView recyclerView;
    AdapterListProduct adapter;
    RecyclerView.LayoutManager layoutManager;

    List<Product> productList;
    ArrayList<Brand> brandList;

    // inflate toolbar into allproductslayout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_activity, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity=this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product);

        toolbar = (Toolbar) findViewById(R.id.toolAction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.electronics);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        Intent intent = getIntent();

        if (intent != null) {
            String KEY_SOURCE = intent.getExtras().getString(Constants.KEY_SOURCE);

            if (KEY_SOURCE.equals(Constants.SOURCE_FROM_MAINACTIVITY)) {

                showProgress();
                //api call to getAllCategories all products
                CommunicationManager.getInstance().getAllProducts(mActivity);
            }
            else if (KEY_SOURCE.equals(Constants.SOURCE_FROM_BRAND)) {

                int brandId = intent.getIntExtra(Constants.KEY_POSITION, 0);
                Log.i(TAG, "...............position........." + brandId);

                String brandName = intent.getStringExtra(Constants.STORED_ITEMS);
                Log.i(TAG, "...............position........." + brandName);

                getSupportActionBar().setTitle(brandName);
                showProgress();
                //api call to getAllCategories product by category
                CommunicationManager.getInstance().getProductsByBrand(mActivity,brandId);

            }
            else if (KEY_SOURCE.equals(Constants.SOURCE_FROM_CATEGORY)) {

                int categoryId = intent.getIntExtra(Constants.KEY_POSITION, 0);
                Log.i(TAG, "...............position........." + categoryId);

                String categoryName = intent.getStringExtra(Constants.STORED_ITEMS);
                Log.i(TAG, "...............position........." + categoryName);

                getSupportActionBar().setTitle(categoryName);
                showProgress();
                //api call to getAllCategories product by brand
                CommunicationManager.getInstance().getProductsByCategory(mActivity,categoryId);
                //ProductAPI.getProductsByCategory(categoryId,this);
            }
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        productList = new ArrayList<>();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public <T> boolean isListNotEmpty(List<T> data) {
        return data != null && !data.isEmpty();
    }

    @Override
    public void onProductCompleted(ProductResponse productResponse) {

        hideProgress();

        if (productResponse.isSuccess()) {
            productList = productResponse.getProducts();
            adapter = new AdapterListProduct(getApplicationContext(), productList);
            layoutManager = new GridLayoutManager(ProductActivity.this, 2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);

        } else {

            ToastUtil.showCenterToast(getApplicationContext(), productResponse.getMessage());
           // Toast.makeText(getApplicationContext(), productResponse.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}





   /*if (isListNotEmpty(productList)) {


   } else {
                Toast.makeText(getApplicationContext(), productResponse.getMessage(), Toast.LENGTH_SHORT).show();

            }*/