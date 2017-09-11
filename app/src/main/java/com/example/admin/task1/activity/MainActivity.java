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

import com.example.admin.task1.R;
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.brand.activity.BrandsActivity;
import com.example.admin.task1.category.activity.AllCategoryActivity;
import com.example.admin.task1.login.LoginActivity;
import com.example.admin.task1.utilities.SessionManager;
import com.example.admin.task1.model.User;
import com.example.admin.task1.product.activity.ProductActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppActivity {

    @BindView(R.id.session_user)            TextView tvLoginUser;
    @BindView(R.id.tv_more)                 TextView tvMore;
    @BindView(R.id.btn_viewAll)             Button btnViewAll;
    @BindView(R.id.tv_login_logout)         TextView btnLogout;
    @BindView(R.id.toolAction)              Toolbar toolbar;
    @BindView(R.id.drawerLayoutMain)        DrawerLayout drawerLayout;
    @BindView(R.id.navi_view)               NavigationView navigationView;

    private static final String TAG = "MainActivity";

    // Session Manager Class
    SessionManager session;
    Gson gson;

    private android.support.v7.app.ActionBarDrawerToggle toggle;

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
        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());
        gson = new Gson();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.appTitle);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fe295aec")));

        // setting navigation drawer in home page
        toggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // providing action with on click listnener for items in navigation drawer
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
                    case R.id.signIn:
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });

        User user= gson.fromJson(session.getUserObject(),User.class);

        if (session.isLoggedIn())
        {
            tvLoginUser.setText(user.getName());
            btnLogout.setText("Logout");

        }
        else {
            tvLoginUser.setText("Welcome to Flipkart");
            btnLogout.setText("Login");
        }
    }


    // intent to allcategories ctivity
    @OnClick(R.id.tv_more)
    public void More(View v) {
        Intent intent = new Intent(v.getContext(), AllCategoryActivity.class);
        startActivity(intent);
        Log.i(TAG, "HIIII");
    }

    // intent to product activity
    @OnClick(R.id.btn_viewAll)
    public void ViewAll(View v) {
        Intent intent = new Intent(v.getContext(), ProductActivity.class);
        intent.setClass(v.getContext(), ProductActivity.class);
        intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FROM_MAINACTIVITY);
        startActivity(intent);
    }

    // Login / Logout button click event
    @OnClick(R.id.tv_login_logout)
    public void LoginLogout(View v) {

        if (session.isLoggedIn())
        {
            // Clear the session data
            session.logoutUser();
            finish();
        }
        else
        {
            // redirect user to LoginActivity
            session.checkLogin();
            finish();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
