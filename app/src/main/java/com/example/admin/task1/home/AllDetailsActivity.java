package com.example.admin.task1.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.admin.task1.R;
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.Product;
import com.example.admin.task1.utilities.OneFragement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllDetailsActivity extends AppActivity {

    @BindView(R.id.toolAction)      Toolbar toolbar;
    @BindView(R.id.tabs)            TabLayout tabLayout;
    @BindView(R.id.viewpager)       ViewPager viewPager;
    @BindView(R.id.fragement)       TextView tvSpec;
    private static final String TAG = "AllDetailsAct";

    int position;
    ArrayList<Product> productList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_all_details);

        ButterKnife.bind(this);

        setToolbar();

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();

        position = intent.getIntExtra(Constants.KEY_POSITION, 0);

        productList = intent.getParcelableArrayListExtra(Constants.STORED_ITEMS);

    }

    public void setToolbar(){
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Product Details");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragement(), "Spec");
        adapter.addFragment(new OneFragement(), "Description");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(OneFragement fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}