package com.example.admin.task1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.task1.brand.activity.BrandsActivity;
import com.example.admin.task1.R;
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.product.activity.ProductActivity;
import com.example.admin.task1.category.activity.AllCategoryActivity;

public class MainActivity extends AppActivity {

    private static final String TAG = "MainActivity";

    Button btn_viewAll;
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;

    private android.support.v7.app.ActionBarDrawerToggle toggle;
    TextView tv_more;

   // inflating menu items into toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        toolbar = (Toolbar) findViewById(R.id.toolAction);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.appTitle);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fe295aec")));

        //setting navigation drawer in home page
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //providing action with on click listnener for items in navigation drawer
        navigationView = (NavigationView) findViewById(R.id.navi_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent intent;
                switch (item.getItemId()) {
                    case R.id.brands:
                        intent = new Intent(getApplicationContext(), BrandsActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "brand Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.offers:
                        Toast.makeText(getApplicationContext(), "offers Selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.book:
                        Toast.makeText(getApplicationContext(), "book Selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.electronics:
                        intent = new Intent(getApplicationContext(), AllCategoryActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "electronics Selected", Toast.LENGTH_LONG).show();
                        return true;
                }
                return true;
            }
        });

        //intent to product activity
        btn_viewAll = (Button) findViewById(R.id.button6);
        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductActivity.class);
                intent.setClass(v.getContext(), ProductActivity.class);
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FROM_MAINACTIVITY);
                startActivity(intent);
            }
        });

        //intent to allcategories ctivity
        tv_more = (TextView) findViewById(R.id.tv_more);
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AllCategoryActivity.class);
                startActivity(intent);
                Log.i(TAG, "HIIII");

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


}
