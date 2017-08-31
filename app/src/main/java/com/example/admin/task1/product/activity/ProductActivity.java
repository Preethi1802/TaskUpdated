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
import android.widget.Toast;

import com.example.admin.task1.R;
import com.example.admin.task1.api.event.ProductAPI;
import com.example.admin.task1.api.response.ProductResponse;
import com.example.admin.task1.api.subscriber.ProductEventSubscriber;
import com.example.admin.task1.api.util.APIUtil;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.Brand;
import com.example.admin.task1.model.Product;
import com.example.admin.task1.product.adapter.AdapterListProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 7/26/2017.
 */

public class ProductActivity extends AppActivity implements ProductEventSubscriber {
    Toolbar toolbar;

    private static final String TAG = "ProductActivity";

    RecyclerView recyclerView;
    AdapterListProduct adapter;
    RecyclerView.LayoutManager layoutManager;

    List<Product> productList;
    ArrayList<Brand> brandList;
    int position = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_all, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product);

        toolbar = (Toolbar) findViewById(R.id.toolAction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mobiles");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        productList = new ArrayList<>();

        APIUtil.getAPI();

        Intent intent = getIntent();

        if (intent != null) {
            position = intent.getIntExtra(APIUtil.KEY_POSITION, 0);
            Log.i(TAG, "...............position........." + position);

            String activity = intent.getExtras().getString(APIUtil.ACTIVITY_CHECK);
            if (activity.equals(APIUtil.ACTIVITY_MAIN)) {
                ProductAPI.get(this);
            } else if (activity.equals(APIUtil.ACTIVITY_BRAND)) {
                int pos = position;
                if (pos == 2) {
                    getSupportActionBar().setTitle("Dell");
                    ProductAPI.getProductsByCategory(this);
                } else {
                    ProductAPI.getProductsByBrand(this);
                }

            }
        }

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
        if (productResponse.isSuccess()) {

           /* if (isListNotEmpty(productList)) {*/
                productList = productResponse.getProducts();
                adapter = new AdapterListProduct(getApplicationContext(), productList);
                layoutManager = new GridLayoutManager(ProductActivity.this, 2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
           /* } else {
                Toast.makeText(getApplicationContext(), productResponse.getMessage(), Toast.LENGTH_SHORT).show();

            }*/
        } else {
            Toast.makeText(getApplicationContext(), productResponse.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

}

