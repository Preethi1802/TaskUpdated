package com.example.admin.task1.settings.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.task1.R;
import com.example.admin.task1.api.event.SettingsAPI;
import com.example.admin.task1.api.response.BrandsResponse;
import com.example.admin.task1.api.response.SettingsResponse;
import com.example.admin.task1.api.subscriber.SettingsEventSubscriber;
import com.example.admin.task1.api.util.APIUtil;
import com.example.admin.task1.model.Brand;
import com.example.admin.task1.product.activity.ProductActivity;
import com.example.admin.task1.settings.adapter.AdapterBrand;

import java.util.ArrayList;

public class BrandsActivity extends AppCompatActivity implements SettingsEventSubscriber{

    private static final String TAG = "BrandsActivity";
    Toolbar toolbar;
    ListView listView;
    ArrayList<Brand> brandList= new ArrayList<>();;
    AdapterBrand adapterBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_brands);

        toolbar = (Toolbar) findViewById(R.id.toolAction);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Brands");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fe295aec")));


        listView = (ListView)findViewById(R.id.brands_list);

        Log.i(TAG, "hiiiiii");

        int position = getIntent().getIntExtra(APIUtil.KEY_POSITION, 0);
        Log.i(TAG, "...............position........." + position);

       /* brandList = getIntent().getParcelableArrayListExtra(APIUtil.STORED_ITEMS);
    //    Log.i(TAG, "..........size............." + brandList.size());*/

        APIUtil.getAPI();
        SettingsAPI.getCategoryListByBrand(this);

    }

    @Override
    public void onBrandCompleted(BrandsResponse brandsResponse) {

        brandList = new ArrayList<Brand>(brandsResponse.getBrand());
        Log.i(TAG,"brandList.size()"+ brandList.size());
        AdapterBrand adapterBrand = new AdapterBrand(brandList);
        listView.setAdapter(adapterBrand);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                long value = listView.getAdapter().getItemId(position);
                Log.i(TAG, "...............position........." + value);

                Intent intent= new Intent(view.getContext(), ProductActivity.class);
                intent.setClass(view.getContext(),ProductActivity.class);
                intent.putExtra(APIUtil.KEY_POSITION,value);
                intent.putExtra("start","From_BrandsActivity");
                startActivity(intent);


            }
        });
    }




    @Override
    public void onSettingsCompleted(SettingsResponse settingsResponse) {

    }

}
