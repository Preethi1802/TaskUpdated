package com.example.admin.task1.settings.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.admin.task1.R;
import com.example.admin.task1.api.event.SettingsAPI;
import com.example.admin.task1.api.response.SettingsResponse;
import com.example.admin.task1.api.subscriber.SettingsEventSubscriber;
import com.example.admin.task1.api.util.APIUtil;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.Category;
import com.example.admin.task1.product.activity.ProductActivity;
import com.example.admin.task1.settings.adapter.AdapterAllCategories;

import java.util.ArrayList;

/**
 * Created by Admin on 8/22/2017.
 */

public class AllCategoryActivity extends AppActivity implements SettingsEventSubscriber {
    private static final String TAG = "ActivityAllCategories";
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private android.support.v7.app.ActionBarDrawerToggle toggle;

    AdapterAllCategories adapterAllCategories;
    ExpandableListView expandableListView;
    ArrayList<Category> categoryList;

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

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayoutAllCategories);
        toggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        expandableListView = (ExpandableListView) findViewById(R.id.expand_list_view);
        categoryList = new ArrayList<>();

        showProgress();
        SettingsAPI.get(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSettingsCompleted(SettingsResponse settingsResponse) {
        hideProgress();

        categoryList = new ArrayList<Category>(settingsResponse.getCategory());
        Log.i(TAG, "categoryList.size()" + categoryList.size());

        adapterAllCategories = new AdapterAllCategories(AllCategoryActivity.this, categoryList);
        expandableListView.setAdapter(adapterAllCategories);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                int value = (int) expandableListView.getAdapter().getItemId(childPosition);
                Log.i(TAG, "...............position........." + value);

                Intent intent = new Intent(v.getContext(), ProductActivity.class);
                intent.setClass(v.getContext(), ProductActivity.class);
                intent.putExtra(APIUtil.KEY_POSITION, value);
                intent.putExtra(APIUtil.ACTIVITY_CHECK, APIUtil.ACTIVITY_BRAND);
                startActivity(intent);
                return false;
            }
        });

    }

}
