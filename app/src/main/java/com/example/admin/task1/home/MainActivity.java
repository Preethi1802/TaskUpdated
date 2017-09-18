package com.example.admin.task1.home;

import android.app.Activity;
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
import com.example.admin.task1.cart.activity.CartActivity;
import com.example.admin.task1.category.activity.AllCategoryActivity;
import com.example.admin.task1.login.LoginActivity;
import com.example.admin.task1.model.User;
import com.example.admin.task1.product.activity.ProductActivity;
import com.example.admin.task1.utilities.SessionManager;
import com.example.admin.task1.wishlist.activity.WishlistActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    MainActivity mActivity;

    // Session Manager Class
    SessionManager session;
    Gson gson;

    private android.support.v7.app.ActionBarDrawerToggle toggle;

    public static void start(final Activity activity) {
        final Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        ButterKnife.bind(this);
        mActivity= this;

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
                    case R.id.navi_brands:
                        intent = new Intent(getApplicationContext(), BrandsActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "brand Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navi_offers:
                        Toast.makeText(getApplicationContext(), "offers Selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.navi_book:
                        Toast.makeText(getApplicationContext(), "book Selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.navi_electronics:
                        intent = new Intent(getApplicationContext(), AllCategoryActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "electronics Selected", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.navi_myAccount:
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navi_whishlist:
                        intent = new Intent(getApplicationContext(), WishlistActivity.class);
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
    public void onMoreClicked(View v) {
        Intent intent = new Intent(v.getContext(), AllCategoryActivity.class);
        startActivity(intent);
        Log.i(TAG, "HIIII");
    }

    // intent to product activity
    @OnClick(R.id.btn_viewAll)
    public void onViewAllClicked(View v) {
        Intent intent = new Intent(v.getContext(), ProductActivity.class);
        intent.setClass(v.getContext(), ProductActivity.class);
        intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FROM_MAINACTIVITY);
        startActivity(intent);
    }

    // Login / Logout button click event
    @OnClick(R.id.tv_login_logout)
    public void onLoginLogoutClicked(View v) {

        if (session.isLoggedIn())
        {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                //FirebaseActivity.start(MainActivity.this);
                            } else {
                               // showSnackbar(R.string.sign_out_failed);
                            }
                        }
                    });
            // Clear the session data
            session.logoutUser();
            tvLoginUser.setText("Welcome to Flipkart");
            btnLogout.setText("Login");
        }
        else
        {
            // redirect user to LoginActivity
            session.checkLogin();
            finish();
        }
    }

    // inflating menu items into toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id= item.getItemId();
        if (id== R.id.cart_toolbar)
        {
            Intent intent= new Intent(this, CartActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
