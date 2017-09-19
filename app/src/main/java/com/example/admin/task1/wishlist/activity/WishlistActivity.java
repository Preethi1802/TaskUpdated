package com.example.admin.task1.wishlist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.task1.api.response.WishListPostAddResponse;
import com.example.admin.task1.api.response.WishListPostRemoveResponse;
import com.example.admin.task1.wishlist.adapter.AdapterWishlist;
import com.example.admin.task1.R;
import com.example.admin.task1.api.request.WishlistRequest;
import com.example.admin.task1.api.response.WishListGetResponse;
import com.example.admin.task1.api.subscriber.WishlistProductEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.Product;
import com.example.admin.task1.model.User;
import com.example.admin.task1.product.activity.ProductDescriptionActivity;
import com.example.admin.task1.utilities.SessionManager;
import com.google.gson.Gson;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 9/15/2017.
 */

public class WishlistActivity extends AppActivity implements WishlistProductEventSubscriber {
    @BindView(R.id.toolAction)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    WishlistActivity mActivity;

    private static final String TAG = "WishlistActivity";

    AdapterWishlist adapter;
    LinearLayoutManager layoutManager;

    List<Product> wishList;

    SessionManager session;
    Gson gson;

    int mRecentlyRemovedPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wishlist);

        mActivity = this;
        layoutManager = new LinearLayoutManager(getApplicationContext());

        ButterKnife.bind(this);

        setToolbar();

        session = new SessionManager(getApplicationContext());
        gson = new Gson();

        wishList = new ArrayList<>();

        adapter = new AdapterWishlist(mActivity, wishList, new AdapterWishlist.WhishlistListener() {
            @Override
            public void onRemoveWhishlist(int position) {
                if (session.isLoggedIn()) {

                    mRecentlyRemovedPosition = position;
                    User user = gson.fromJson(session.getUserObject(), User.class);

                    WishlistRequest wishlistRequest = new WishlistRequest();
                    wishlistRequest.setUser_id(user.getId());
                    wishlistRequest.setProduct_id(wishList.get(position).getId());

                    Log.i(TAG, "" + user.getId());

                    showProgress();
                    CommunicationManager.getInstance().postRemoveFromWhishlist(wishlistRequest, mActivity);
                }
            }

            @Override
            public void onViewItem(View view, int position) {
                ProductDescriptionActivity.start(view.getContext(), wishList.get(position));
            }
        });
        layoutManager = new GridLayoutManager(WishlistActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        User user = gson.fromJson(session.getUserObject(), User.class);
        //call api to get wishlist products
        if (session.isLoggedIn()) {
            showProgress();
            CommunicationManager.getInstance().getWhishlistProducts(mActivity, user.getId());
        } else {
            ToastUtil.showCenterToast(getApplicationContext(), "Your Cart Is Empty");
        }
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.whishList);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetWhishlistCompleted(WishListGetResponse wishListGetResponse) {
        hideProgress();
        if (wishListGetResponse.isSuccess()) {
            wishList.addAll(wishListGetResponse.getProducts());
            adapter.notifyDataSetChanged();
        } else {
            ToastUtil.showCenterToast(getApplicationContext(), wishListGetResponse.getMessage());
        }
    }

    @Override
    public void onAddWishListCompleted(WishListPostAddResponse wishListPostAddResponse) {

    }

    @Override
    public void onRemoveWishListCompleted(WishListPostRemoveResponse wishListPostRemoveResponse) {
        hideProgress();
        if (wishListPostRemoveResponse.isSuccess()) {
            wishList.remove(mRecentlyRemovedPosition);
            adapter.notifyDataSetChanged();
            ToastUtil.showCenterToast(mActivity, wishListPostRemoveResponse.getMessage());
        } else {
            ToastUtil.showCenterToast(getApplicationContext(), wishListPostRemoveResponse.getMessage());
        }
    }
}
