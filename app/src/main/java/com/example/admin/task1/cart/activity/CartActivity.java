package com.example.admin.task1.cart.activity;

import android.content.Intent;
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

import com.example.admin.task1.R;
import com.example.admin.task1.api.request.CartRequest;
import com.example.admin.task1.api.request.WishlistRequest;
import com.example.admin.task1.api.response.AddCartResponse;
import com.example.admin.task1.api.response.AddWishListResponse;
import com.example.admin.task1.api.response.GetCartResponse;
import com.example.admin.task1.api.response.RemoveCartResponse;
import com.example.admin.task1.api.response.GetWishListResponse;
import com.example.admin.task1.api.response.RemoveWishListResponse;
import com.example.admin.task1.api.subscriber.CartEventSubscriber;
import com.example.admin.task1.api.subscriber.WishlistProductEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.cart.adapter.AdapterCart;
import com.example.admin.task1.login.LoginActivity;
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
 * Created by Admin on 9/14/2017.
 */

public class CartActivity extends AppActivity implements CartEventSubscriber ,WishlistProductEventSubscriber
{

    @BindView(R.id.toolAction)              Toolbar toolbar;
    @BindView(R.id.recycler_view)           RecyclerView recyclerView;

    CartActivity mActivity;

    private static final String TAG = "CartActivity";

    AdapterCart adapter;
    LinearLayoutManager layoutManager;

    List<Product> cartList;
    // inflate toolbar into allproductslayout

    SessionManager session;
    Gson gson;

    int mRecentlyRemovedPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart);

        mActivity = this;
        layoutManager = new LinearLayoutManager(getApplicationContext());

        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());
        gson = new Gson();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.cart);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        cartList = new ArrayList<>();
        adapter = new AdapterCart(mActivity, cartList, new AdapterCart.CartListener() {
            @Override
            public void onAddToWhishlit(int position) {

                if (session.isLoggedIn()) {
                    User user= gson.fromJson(session.getUserObject(),User.class);

                    WishlistRequest wishlistRequest = new WishlistRequest();
                    wishlistRequest.setUser_id(user.getId());
                    wishlistRequest.setProduct_id(cartList.get(position).getId());

                    Log.i(TAG,""+user.getId());

                    showProgress();
                    CommunicationManager.getInstance().postAddProductsToWhishlist(wishlistRequest,mActivity);

                } else {
                    ToastUtil.showCenterToast(getApplicationContext(),"Login or SignUp to Continue");
                    Intent intent= new Intent(mActivity, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onRemoveFromCart(int position) {

                if (session.isLoggedIn()) {
                    mRecentlyRemovedPosition= position;
                    User user= gson.fromJson(session.getUserObject(),User.class);

                    CartRequest cartRequest = new CartRequest();
                    cartRequest.setUser_id(user.getId());
                    cartRequest.setProduct_id(cartList.get(position).getId());

                    Log.i(TAG,""+user.getId());

                    showProgress();
                    CommunicationManager.getInstance().postRemoveCart(cartRequest,mActivity);
                }
            }
            @Override
            public void onViewItem(View view, int position) {

                ProductDescriptionActivity.start(view.getContext(), cartList.get(position));

            }
        });
        layoutManager = new GridLayoutManager(CartActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        User user= gson.fromJson(session.getUserObject(),User.class);

        // call api to get cart products
        if (session.isLoggedIn()) {
            showProgress();
            CommunicationManager.getInstance().getCart(mActivity, user.getId());
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
    public void onGetCartCompleted(GetCartResponse getCartResponse) {
        hideProgress();
        if (getCartResponse.isSuccess())
        {
            cartList.addAll(getCartResponse.getProducts());
            adapter.notifyDataSetChanged();
        }
        else {
            ToastUtil.showCenterToast(getApplicationContext(), getCartResponse.getMessage());
        }

    }

    @Override
    public void onAddCartCompleted(AddCartResponse addCartResponse) {

    }

    @Override
    public void onRemoveCartCompleted(RemoveCartResponse removeCartResponse) {
        hideProgress();
        if (removeCartResponse.isSuccess())
        {
            cartList.remove(mRecentlyRemovedPosition);
            adapter.notifyDataSetChanged();
            ToastUtil.showCenterToast(mActivity, removeCartResponse.getMessage());
        }
        else {
            ToastUtil.showCenterToast(mActivity, removeCartResponse.getMessage());
        }
    }

    @Override
    public void onGetWhishlistCompleted(GetWishListResponse getWishListResponse) {

    }

    @Override
    public void onAddWishListCompleted(AddWishListResponse addWishListResponse) {
        hideProgress();
        if (addWishListResponse.isSuccess())
        {
            adapter.notifyDataSetChanged();
            ToastUtil.showCenterToast(mActivity, addWishListResponse.getMessage());

        }
        else {
            ToastUtil.showCenterToast(getApplicationContext(), addWishListResponse.getMessage());
        }
    }

    @Override
    public void onRemoveWishListCompleted(RemoveWishListResponse removeWishListResponse) {

    }
}
