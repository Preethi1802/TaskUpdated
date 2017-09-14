package com.example.admin.task1.cart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.admin.task1.R;
import com.example.admin.task1.api.response.CartResponse;
import com.example.admin.task1.api.subscriber.CartEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.cart.adapter.AdapterCart;
import com.example.admin.task1.home.MainActivity;
import com.example.admin.task1.model.Product;
import com.example.admin.task1.model.User;
import com.example.admin.task1.product.activity.ProductDescriptionActivity;
import com.example.admin.task1.utilities.SessionManager;
import com.google.gson.Gson;
import com.thapovan.android.commonutils.recyclerview.RecyclerTouchListener;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 9/14/2017.
 */

public class CartActivity extends AppActivity implements CartEventSubscriber
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
        adapter = new AdapterCart(mActivity, cartList);
        layoutManager = new GridLayoutManager(CartActivity.this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        User user= gson.fromJson(session.getUserObject(),User.class);

        showProgress();
        CommunicationManager.getInstance().getCart(mActivity,user.getId());

        new RecyclerTouchListener(mActivity, recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(final View view, final int position) {

               // Log.i(TAG, Constants.STORED_ITEMS + position);
                ProductDescriptionActivity.start(mActivity, cartList.get(position));

                if (view.getId()== R.id.move_to_whishlist)
                {
                    Intent intent = new Intent(mActivity, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onLongClick(final View view, final int position) {
            }

        });

    }

    @Override
    public void onCartCompleted(CartResponse cartResponse) {
        hideProgress();
        if (cartResponse.isSuccess())
        {
            cartList.addAll(cartResponse.getProducts());
            adapter.notifyDataSetChanged();
            ToastUtil.showCenterToast(mActivity, cartResponse.getMessage());
        }
        else {
            ToastUtil.showCenterToast(getApplicationContext(), cartResponse.getMessage());

        }

    }
}
