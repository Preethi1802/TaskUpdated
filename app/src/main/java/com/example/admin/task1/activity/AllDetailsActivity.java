package com.example.admin.task1.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.example.admin.task1.R;
import com.example.admin.task1.adapter.AdapterTabPage;

public class AllDetailsActivity extends FragmentActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    private AdapterTabPage adapterTabPage;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = {"Top Rated", "Games", "Movies"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_all_details);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        //    actionBar = getActionBar();
        adapterTabPage = new AdapterTabPage(getSupportFragmentManager());

        viewPager.setAdapter(adapterTabPage);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}