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

import com.example.admin.task1.api.response.AddWishListResponse;
import com.example.admin.task1.api.response.RemoveWishListResponse;
import com.example.admin.task1.wishlist.adapter.AdapterWishlist;
import com.example.admin.task1.R;
import com.example.admin.task1.api.request.WishlistRequest;
import com.example.admin.task1.api.response.GetWishListResponse;
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

public class WishlistActivity extends AppActivity implements WishlistProductEventSubscriber
{
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

        session = new SessionManager(getApplicationContext());
        gson = new Gson();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.whishList);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        wishList = new ArrayList<>();

        adapter= new AdapterWishlist(mActivity, wishList, new AdapterWishlist.WhishlistListener() {
            @Override
            public void onRemoveWhishlist(int position) {
                if (session.isLoggedIn()) {

                    mRecentlyRemovedPosition = position;
                    User user= gson.fromJson(session.getUserObject(),User.class);

                    WishlistRequest wishlistRequest = new WishlistRequest();
                    wishlistRequest.setUser_id(user.getId());
                    wishlistRequest.setProduct_id(wishList.get(position).getId());

                    Log.i(TAG,""+user.getId());

                    showProgress();
                    CommunicationManager.getInstance().postRemoveFromWhishlist(wishlistRequest,mActivity);
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

        User user= gson.fromJson(session.getUserObject(),User.class);
        //call api to get wishlist products
        if (session.isLoggedIn()) {
            showProgress();
            CommunicationManager.getInstance().getWhishlistProducts(mActivity, user.getId());
        }
        else {
            ToastUtil.showCenterToast(getApplicationContext(),"Your Cart Is Empty");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetWhishlistCompleted(GetWishListResponse getWishListResponse) {
        hideProgress();
        if (getWishListResponse.isSuccess())
        {
            wishList.addAll(getWishListResponse.getProducts());
            adapter.notifyDataSetChanged();
        }
        else {
            ToastUtil.showCenterToast(getApplicationContext(), getWishListResponse.getMessage());
        }
    }
    @Override
    public void onAddWishListCompleted(AddWishListResponse addWishListResponse) {

    }

    @Override
    public void onRemoveWishListCompleted(RemoveWishListResponse removeWishListResponse) {
        hideProgress();
        if (removeWishListResponse.isSuccess())
        {
        wishList.remove(mRecentlyRemovedPosition);
        adapter.notifyDataSetChanged();
        ToastUtil.showCenterToast(mActivity, removeWishListResponse.getMessage());
        }
        else {
            ToastUtil.showCenterToast(getApplicationContext(), removeWishListResponse.getMessage());
        }
    }
}
