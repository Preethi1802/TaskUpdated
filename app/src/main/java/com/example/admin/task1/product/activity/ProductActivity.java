package com.example.admin.task1.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.task1.R;
import com.example.admin.task1.api.response.ProductResponse;
import com.example.admin.task1.api.subscriber.ProductEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.api.util.RecyclerViewInfiniteScrollListener;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.cart.activity.CartActivity;
import com.example.admin.task1.model.Brand;
import com.example.admin.task1.model.Product;
import com.example.admin.task1.product.adapter.AdapterListProduct;
import com.thapovan.android.commonutils.recyclerview.RecyclerTouchListener;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 7/26/2017.
 */

public class ProductActivity extends AppActivity implements ProductEventSubscriber {

    @BindView(R.id.toolAction)              Toolbar toolbar;
    @BindView(R.id.recycler_view)           RecyclerView recyclerView;

    ProductActivity mActivity;

    private static final String TAG = "ProductActivity";

    AdapterListProduct adapter;
    LinearLayoutManager layoutManager;

    List<Product> productList;
    ArrayList<Brand> brandList;

    // inflate toolbar into allproductslayout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        layoutManager = new LinearLayoutManager(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.electronics);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        productList = new ArrayList<>();
        adapter = new AdapterListProduct(mActivity, productList);
        layoutManager = new GridLayoutManager(ProductActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();

        if (intent != null) {
            String KEY_SOURCE = intent.getExtras().getString(Constants.KEY_SOURCE);

            if (KEY_SOURCE.equals(Constants.SOURCE_FROM_MAINACTIVITY)) {

                int page = 1;
                showProgress();
                //api call to getAllCategories all products
                CommunicationManager.getInstance().getAllProducts(mActivity, page);

                recyclerView.addOnScrollListener(new RecyclerViewInfiniteScrollListener(layoutManager) {
                    @Override
                    public void loadMore(int page) {
                        CommunicationManager.getInstance().getAllProducts(mActivity, page);
                    }
                });

            } else if (KEY_SOURCE.equals(Constants.SOURCE_FROM_BRAND)) {

                int brandId = intent.getIntExtra(Constants.KEY_POSITION, 0);
                Log.i(TAG, "...............position........." + brandId);

                String brandName = intent.getStringExtra(Constants.STORED_ITEMS);
                Log.i(TAG, "...............position........." + brandName);

                getSupportActionBar().setTitle(brandName);
                showProgress();
                //api call to getAllCategories product by category
                CommunicationManager.getInstance().getProductsByBrand(mActivity, brandId);

            } else if (KEY_SOURCE.equals(Constants.SOURCE_FROM_CATEGORY)) {

                int categoryId = intent.getIntExtra(Constants.KEY_POSITION, 0);
                Log.i(TAG, "...............position........." + categoryId);

                String categoryName = intent.getStringExtra(Constants.STORED_ITEMS);
                Log.i(TAG, "...............position........." + categoryName);

                getSupportActionBar().setTitle(categoryName);
                showProgress();
                //api call to getAllCategories product by brand
                CommunicationManager.getInstance().getProductsByCategory(mActivity, categoryId);
                //ProductAPI.getProductsByCategory(categoryId,this);
            }
        }



        new RecyclerTouchListener(mActivity, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(final View view, final int position) {
                Log.i(TAG, Constants.STORED_ITEMS + position);

                /*Intent intent = new Intent(view.getContext(), ProductDescriptionActivity.class);
                *//*intent.putExtra(Constants.KEY_POSITION, position);
                intent.putParcelableArrayListExtra(Constants.STORED_ITEMS, (ArrayList<? extends Parcelable>) productList);*//*
                intent.putExtra(Constants.KEY_EXTRA_PRODUCT,productList.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/
                ProductDescriptionActivity.start(mActivity, productList.get(position));
            }

            @Override
            public void onLongClick(final View view, final int position) {
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        if (id== R.id.cart_toolbar)
        {
            Intent intent= new Intent(this, CartActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public <T> boolean isListNotEmpty(List<T> data) {
        return data != null && !data.isEmpty();
    }

    @Override
    public void onProductCompleted(ProductResponse productResponse) {
        Log.d(TAG, "onProductCompleted() called with: productResponse = [" + productResponse.isSuccess() + "]");
        Log.d(TAG, "onProductCompleted() called with: productResponse = [" + productResponse.getMessage() + "]");

        hideProgress();

        if (productResponse.isSuccess()) {
            productList.addAll(productResponse.getProducts());
            adapter.notifyDataSetChanged();

        } else {
            ToastUtil.showCenterToast(getApplicationContext(), productResponse.getMessage());
        }
    }
}
