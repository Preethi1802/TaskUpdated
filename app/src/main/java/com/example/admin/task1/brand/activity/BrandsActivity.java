package com.example.admin.task1.brand.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.task1.brand.adapter.AdapterBrand;
import com.example.admin.task1.R;
import com.example.admin.task1.api.event.SettingsAPI;
import com.example.admin.task1.api.response.SettingsResponse;
import com.example.admin.task1.api.subscriber.SettingsEventSubscriber;
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.Brand;
import com.example.admin.task1.product.activity.ProductActivity;

import java.util.ArrayList;

public class BrandsActivity extends AppActivity implements SettingsEventSubscriber {

    private static final String TAG = "BrandsActivity";
    Toolbar toolbar;
    ListView listView;
    ArrayList<Brand> brandList = new ArrayList<>();
    AdapterBrand adapterBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_brands);

        toolbar = (Toolbar) findViewById(R.id.toolAction);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.brand);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fe295aec")));

        listView = (ListView) findViewById(R.id.brands_list);
        Log.i(TAG, "hiiiiii");

        //api call to getAllCategories categories by brand
        showProgress();
        SettingsAPI.getCategoryListByBrand(this);
    }
    @Override
    public void onSettingsCompleted(SettingsResponse settingsResponse) {
        hideProgress();

        brandList = new ArrayList<Brand>(settingsResponse.getBrand());
        Log.i(TAG, "brandList.size()" + brandList.size());
        adapterBrand = new AdapterBrand(brandList);
        listView.setAdapter(adapterBrand);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int brandId = brandList.get(position).getId();
                Log.i(TAG, "...............position........." + brandId);

                String brandName = brandList.get(position).getName();
                Log.i(TAG, "...............brandName........." + brandName);

                //intent to product activity for getting products by brand
                Intent intent = new Intent(view.getContext(), ProductActivity.class);
                intent.setClass(view.getContext(), ProductActivity.class);
                intent.putExtra(Constants.KEY_POSITION, brandId);
                intent.putExtra(Constants.STORED_ITEMS,brandName);
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FROM_BRAND);
                startActivity(intent);
            }
        });
    }
}