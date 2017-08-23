package com.example.admin.task1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.task1.API.request.APIClient;
import com.example.admin.task1.API.request.ApiInterface;
import com.example.admin.task1.API.response.ApiResponse;
import com.example.admin.task1.R;
import com.example.admin.task1.adapter.AdapterProduct;
import com.example.admin.task1.model.Product;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 7/26/2017.
 */

public class ActivityProduct extends AppCompatActivity {
    Toolbar toolbar;

    private static final String TAG = "ActivityProduct";

    RecyclerView recyclerView;
    AdapterProduct adapter;
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

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiInterface.getProductDetails();

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse apiResponse = response.body();
                productList = new ArrayList<>(Arrays.asList(apiResponse.getProduct()));

                adapter = new AdapterProduct(getApplicationContext(), productList);
                layoutManager = new GridLayoutManager(ActivityProduct.this, 2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
