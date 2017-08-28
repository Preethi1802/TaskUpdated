package com.example.admin.task1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.admin.task1.API.request.APIClient;
import com.example.admin.task1.API.request.SettingsRequest;
import com.example.admin.task1.API.response.SettingsResponse;
import com.example.admin.task1.R;
import com.example.admin.task1.adapter.AdapterAllCategories;
import com.example.admin.task1.model.Category;
import com.example.admin.task1.rest.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 8/22/2017.
 */

public class ActivityAllCategoriesListView extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private static final String TAG = "ActivityAllCategories";
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private android.support.v7.app.ActionBarDrawerToggle toggle;

    AdapterAllCategories adapterAllCategories;
    ExpandableListView expandableListView;
    ArrayList<Category> cList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_all, menu);
        return true;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_all_categories_list);

        toolbar = (Toolbar) findViewById(R.id.toolAction);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Categories");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fe295aec")));

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayoutAllCategories);
        toggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        expandableListView= (ExpandableListView) findViewById(R.id.expand_list_view);
        cList= new ArrayList<>();


        SettingsRequest settingsRequest = APIClient.getClient().create(SettingsRequest.class);
        Call<SettingsResponse> call = settingsRequest.getCategoriesdetail();
        call.enqueue(new Callback<SettingsResponse>() {
            @Override
            public void onResponse(Call<SettingsResponse> call, Response<SettingsResponse> response) {
                SettingsResponse settingsResponse = response.body();
                cList = new ArrayList<Category>(settingsResponse.getCategory());
                Log.i(TAG,"cList.size()"+cList.size());


                adapterAllCategories = new AdapterAllCategories(ActivityAllCategoriesListView.this,cList);
                expandableListView.setAdapter(adapterAllCategories);

            }

            @Override
            public void onFailure(Call<SettingsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent= new Intent(view.getContext(),ActivityAllCategoriesListView.class);

        intent.putExtra(Constants.KEY_POSITION, position);
        view.getContext().startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(),"Item " + (position + 1) + ": " + cList.get(position),Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
