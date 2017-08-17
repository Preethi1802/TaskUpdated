package com.example.admin.task1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 7/26/2017.
 */

public class ProductsActivity extends AppCompatActivity {
    Toolbar toolbar;

    RecyclerView recyclerView;
    AdapterForProducts adapter;
    RecyclerView.LayoutManager layoutManager;
  /*  String[] mobileName, version;
    int[] image = {R.drawable.s1_mob1, R.drawable.s1_mob2, R.drawable.s1_mob3, R.drawable.s1_mob4};


    String[] mobilePrize, mobileRating, ratingInwords, url;
    int[] imgView1 = {R.drawable.lenova2, R.drawable.fierce_xl_view1, R.drawable.iphone_view1, R.drawable.lg_view1};
    int[] imgView2 = {R.drawable.lenova3, R.drawable.fierce_xl_view2, R.drawable.iphone_view2, R.drawable.lg_view2};
    int[] imgView3 = {R.drawable.lenova4, R.drawable.fierce_xl_view3, R.drawable.iphone_view3, R.drawable.lg_view3,};
    int[] imgView4 = {R.drawable.lenova5, R.drawable.fierce_xl_view4, R.drawable.iphone_view4, R.drawable.lg_view4};*/

    ArrayList<Products> productsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_view);

        toolbar = (Toolbar) findViewById(R.id.toolAction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mobiles");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
       /* ImageLoaderConfiguration config= new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);*/
        productsList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = requestInterface.getProductDetails();

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse= response.body();
                productsList=  new ArrayList<>(Arrays.asList(jsonResponse.getProductResponse()));

                adapter = new AdapterForProducts(getApplicationContext(), productsList);
                layoutManager = new GridLayoutManager(ProductsActivity.this, 2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

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
