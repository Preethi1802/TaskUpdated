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
import android.widget.TextView;

import com.example.admin.task1.R;
import com.example.admin.task1.api.request.CartRequest;
import com.example.admin.task1.api.request.WishlistRequest;
import com.example.admin.task1.api.response.CartGetResponse;
import com.example.admin.task1.api.response.CartPostAddResponse;
import com.example.admin.task1.api.response.CartPostRemoveResponse;
import com.example.admin.task1.api.response.WishListGetResponse;
import com.example.admin.task1.api.response.WishListPostAddResponse;
import com.example.admin.task1.api.response.WishListPostRemoveResponse;
import com.example.admin.task1.api.subscriber.CartEventSubscriber;
import com.example.admin.task1.api.subscriber.WishlistProductEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.cart.adapter.AdapterCart;
import com.example.admin.task1.login.LoginActivity;
import com.example.admin.task1.model.Cart;
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

public class CartActivity extends AppActivity implements CartEventSubscriber, WishlistProductEventSubscriber {

    @BindView(R.id.toolAction)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    //   @BindView(R.id.tv_quantity_at_getCart)  TextView tvQuantityAtGetCart;

    CartActivity mActivity;

    private static final String TAG = "CartActivity";

    AdapterCart adapter;
    LinearLayoutManager layoutManager;

    List<Cart> cartList;
    Cart cartProduct;

    SessionManager session;
    Gson gson;

    int mRecentlyRemovedPosition;
    int mQuantity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart);

        mActivity = this;
        layoutManager = new LinearLayoutManager(getApplicationContext());

        ButterKnife.bind(this);

        setToolbar();

        session = new SessionManager(getApplicationContext());
        gson = new Gson();

        cartList = new ArrayList<>();
        adapter = new AdapterCart(mActivity, cartList, new AdapterCart.CartListener() {
            @Override
            public void onAddToWhishlit(int position) {


                if (SessionManager.getInstance().isLoggedIn()) {
                    User user = gson.fromJson(session.getUserObject(), User.class);

                    WishlistRequest wishlistRequest = new WishlistRequest();
                    wishlistRequest.setUser_id(user.getId());
                    wishlistRequest.setProduct_id(cartList.get(position).getProduct().getId());

                    Log.i(TAG, "" + user.getId());

                    showProgress();
                    CommunicationManager.getInstance().postAddProductsToWhishlist(wishlistRequest, mActivity);

                } else {
                    ToastUtil.showCenterToast(getApplicationContext(), "Login or SignUp to Continue");
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onRemoveFromCart(int position) {

                if (session.isLoggedIn()) {
                    mRecentlyRemovedPosition = position;
                    User user = gson.fromJson(session.getUserObject(), User.class);

                    CartRequest cartRequest = new CartRequest();
                    cartRequest.setUser_id(user.getId());
                    cartRequest.setProduct_id(cartList.get(position).getProduct().getId());

                    Log.i(TAG, "" + user.getId());

                    showProgress();
                    CommunicationManager.getInstance().postRemoveCart(cartRequest, mActivity);
                }
            }

            @Override
            public void onViewItem(View view, int position) {

                ProductDescriptionActivity.start(view.getContext(), cartList.get(position).getProduct());

            }

            @Override
            public void onIncreaseButtonClicked(TextView view, int position) {
                mQuantity = cartList.get(position).getQuantity() + 1;
                view.setText(String.valueOf(mQuantity));

                cart(position);
                onQuantityAtCart(view, mQuantity);

            }

            @Override
            public void onDecreaseButtonClicked(TextView view, int position) {

                mQuantity = cartList.get(position).getQuantity() - 1;
                view.setText(String.valueOf(mQuantity));
                cart(position);
                onQuantityAtCart(view, mQuantity);
            }

            @Override
            public void onQuantityAtCart(TextView view, int num) {
                view.setText(String.valueOf(num));
            }

        });
        layoutManager = new GridLayoutManager(CartActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        User user = gson.fromJson(session.getUserObject(), User.class);

        // call api to get cart products
        if (session.isLoggedIn()) {
            showProgress();
            CommunicationManager.getInstance().getCart(mActivity, user.getId());
        } else {
            ToastUtil.showCenterToast(getApplicationContext(), "Your Cart Is Empty");
        }
    }

    public void cart(int position) {
        if (session.isLoggedIn()) {
            User user = gson.fromJson(session.getUserObject(), User.class);

            CartRequest cartRequest = new CartRequest();
            cartRequest.setUser_id(user.getId());
            cartRequest.setProduct_id(cartList.get(position).getProduct().getId());
            //  cartRequest.setQuantity(TextUtil.cleanupString(tvQuantityAtGetCart.getText().toString().trim()));

            Log.i(TAG, "" + user.getId());
            showProgress();
            CommunicationManager.getInstance().postAddCart(cartRequest, mActivity);

        } else {
            ToastUtil.showCenterToast(getApplicationContext(), "Login or SignUp to Continue");
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.cart);

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
    public void onGetCartCompleted(CartGetResponse cartGetResponse) {
        hideProgress();
        if (cartGetResponse.isSuccess()) {
            cartList.addAll(cartGetResponse.getCart());
            adapter.notifyDataSetChanged();
        } else {
            ToastUtil.showCenterToast(getApplicationContext(), cartGetResponse.getMessage());
        }
    }

    @Override
    public void onAddCartCompleted(CartPostAddResponse cartPostAddResponse) {
        hideProgress();
        if (cartPostAddResponse.isSuccess()) {

        } else {
            ToastUtil.showCenterToast(getApplicationContext(), cartPostAddResponse.getMessage());
        }

    }

    @Override
    public void onRemoveCartCompleted(CartPostRemoveResponse cartPostRemoveResponse) {
        hideProgress();
        if (cartPostRemoveResponse.isSuccess()) {
            cartList.remove(mRecentlyRemovedPosition);
            adapter.notifyDataSetChanged();
            ToastUtil.showCenterToast(mActivity, cartPostRemoveResponse.getMessage());
        } else {
            ToastUtil.showCenterToast(mActivity, cartPostRemoveResponse.getMessage());
        }
    }

    @Override
    public void onGetWhishlistCompleted(WishListGetResponse wishListGetResponse) {

    }

    @Override
    public void onAddWishListCompleted(WishListPostAddResponse wishListPostAddResponse) {
        hideProgress();
        if (wishListPostAddResponse.isSuccess()) {
            adapter.notifyDataSetChanged();
            ToastUtil.showCenterToast(mActivity, wishListPostAddResponse.getMessage());

        } else {
            ToastUtil.showCenterToast(getApplicationContext(), wishListPostAddResponse.getMessage());
        }
    }

    @Override
    public void onRemoveWishListCompleted(WishListPostRemoveResponse wishListPostRemoveResponse) {

    }
}
