package com.example.admin.task1.product.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.task1.R;
import com.example.admin.task1.product.adapter.AdapterListProduct;
import com.example.admin.task1.api.event.ProductAPI;
import com.example.admin.task1.api.response.ProductResponse;
import com.example.admin.task1.api.subscriber.ProductEventSubscriber;
import com.example.admin.task1.api.util.APIUtil;
import com.example.admin.task1.model.Product;

import java.util.ArrayList;

/**
 * Created by Admin on 7/26/2017.
 */

public class ProductActivity extends AppCompatActivity implements ProductEventSubscriber{
    Toolbar toolbar;

    private static final String TAG = "ProductActivity";

    RecyclerView recyclerView;
    AdapterListProduct adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Product> productList;

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
        ProductAPI.get(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProductCompleted(ProductResponse productResponse) {
        productList = new ArrayList<Product>(productResponse.getProducts());

        adapter = new AdapterListProduct(getApplicationContext(), productList);
        layoutManager = new GridLayoutManager(ProductActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
}
