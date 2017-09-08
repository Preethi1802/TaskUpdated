package com.example.admin.task1.category.activity;

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
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.Category;
import com.example.admin.task1.product.activity.ProductActivity;
import com.example.admin.task1.category.adapter.AdapterAllCategories;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 8/22/2017.
 */

public class AllCategoryActivity extends AppActivity implements SettingsEventSubscriber {

    @BindView(R.id.toolAction)                          Toolbar toolbar;
    @BindView(R.id.drawerlayoutAllCategories)           DrawerLayout drawerLayout;
    @BindView(R.id.expand_list_view)                    ExpandableListView expandableListView;


    AllCategoryActivity mActivity;
    private static final String TAG = "ActivityAllCategories";
    private android.support.v7.app.ActionBarDrawerToggle toggle;

    AdapterAllCategories adapterAllCategories;
    ArrayList<Category> categoryList;

    //inflate menu items into toolbar in all categories layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_activity, menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity= this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_all_categories_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.allCategories);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fe295aec")));

        //setting navigation drawer in all categories layout
        toggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        categoryList = new ArrayList<>();

        showProgress();
        CommunicationManager.getInstance().getAllCategories(mActivity);
        SettingsAPI.getAllCategories(this);
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

               // int value = (int) expandableListView.getAdapter().getItemId(childPosition);
                int categoryId = categoryList.get(groupPosition).getChildren().get(childPosition).getId();
                String childCategory = categoryList.get(groupPosition).getChildren().get(childPosition).getName();
                Log.i(TAG, "...............position........." + categoryId);

                //intent to product activity for getting all categories list
                Intent intent = new Intent(v.getContext(), ProductActivity.class);
                intent.setClass(v.getContext(), ProductActivity.class);
                intent.putExtra(Constants.KEY_POSITION, categoryId);
                intent.putExtra(Constants.STORED_ITEMS, childCategory);
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FROM_CATEGORY);
                startActivity(intent);
                return false;
            }
        });
    }
}
