package com.example.admin.task1.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.task1.R;
import com.example.admin.task1.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 9/14/2017.
 */

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {

    private static final String TAG = "AdapterCart";
    List<Product> cartList = new ArrayList<>();
    public Context mContext;

    public AdapterCart(Context applicationContext, List<Product> cartList) {
        this.cartList = cartList;
        this.mContext = applicationContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mContext, cartList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        Product cart = cartList.get(position);
        holder.tvMobileName.setText(cart.getName());
        holder.tvMobilePrize.setText(cart.getRegularPrice());
        Log.i(TAG, "%%%%%%%%%%%%%%%%%%" + cart.getFeaturedImages());

        String imageURL = cart.getFeaturedImages().getFeaturedImageURL();

        Glide.with(mContext)
                .load(imageURL)
                .into(holder.ivProductImage);

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.s1_mob1)
        ImageView ivProductImage;
        @BindView(R.id.mobileName)
        TextView tvMobileName;
        @BindView(R.id.version)
        TextView tvMobilePrize;

        List<Product> items;
        Context ctx;

        public ViewHolder(View itemView, Context ctx, List<Product> items) {
            super(itemView);
            this.items = items;
            this.ctx = ctx;
            ButterKnife.bind(this, itemView);

        }
    }
}
