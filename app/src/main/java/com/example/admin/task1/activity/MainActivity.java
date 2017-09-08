package com.example.admin.task1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
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
import com.example.admin.task1.login.LoginActivity;
import com.example.admin.task1.login.SessionManager;
import com.example.admin.task1.product.activity.ProductActivity;
import com.example.admin.task1.category.activity.AllCategoryActivity;

import java.util.HashMap;

public class MainActivity extends AppActivity {

    private static final String TAG = "MainActivity";

    Button btnViewAll;
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;

    // Session Manager Class
    SessionManager session;

    // Button Logout
    Button btnLogout;

    private android.support.v7.app.ActionBarDrawerToggle toggle;
    TextView tvMore;

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

        // setting navigation drawer in home page
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // providing action with on click listnener for items in navigation drawer
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
                    case R.id.signIn:
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });

        // intent to product activity
        btnViewAll = (Button) findViewById(R.id.button6);
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductActivity.class);
                intent.setClass(v.getContext(), ProductActivity.class);
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FROM_MAINACTIVITY);
                startActivity(intent);
            }
        });

        // intent to allcategories ctivity
        tvMore = (TextView) findViewById(R.id.tv_more);
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AllCategoryActivity.class);
                startActivity(intent);
                Log.i(TAG, "HIIII");

            }
        });

//*********************************SESSION MANAGEMENT*************************************************************************
        session = new SessionManager(getApplicationContext());

        TextView tvLoginUser = (TextView) findViewById(R.id.session_user);
        TextView tvLoginEmail = (TextView) findViewById(R.id.session_email);

        HashMap<String, String> user = session.getUserDetails();
        // name
        String name = user.get(SessionManager.KEY_NAME);
        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        // displaying user data in home page
        tvLoginUser.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        tvLoginEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));


        btnLogout = (Button) findViewById(R.id.btn_login_logout);
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        if (session.isLoggedIn()== false)
        {
            btnLogout.setText("Login");
        }
        else {
            btnLogout.setText("Logout");
        }
        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (session.isLoggedIn()== false)
                {
                    // redirect user to LoginActivity
                    session.checkLogin();
                    finish();
                }
                else
                {
                    // Clear the session data
                    session.logoutUser();
                    finish();
                }
            }
        });
//*********************************SESSION MANAGEMENT*************************************************************************
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
