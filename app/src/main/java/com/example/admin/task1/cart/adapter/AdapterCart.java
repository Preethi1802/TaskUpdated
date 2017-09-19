package com.example.admin.task1.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.task1.R;
import com.example.admin.task1.model.Cart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 9/14/2017.
 */

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {
    private static final String TAG = "AdapterCart";

    CartListener cartListener;

    List<Cart> cartList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    public Context mContext;

    public AdapterCart(Context context, List<Cart> cartList, CartListener cartListener) {
        layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cartList = cartList;
        this.mContext = context;
        this.cartListener = cartListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cart_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


       // Product cart = cartList.get(position);
        Cart cartProduct = cartList.get(position);
        holder.tvMobileName.setText(cartProduct.getProduct().getName());
        holder.tvMobilePrize.setText(cartProduct.getProduct().getRegularPrice());
        holder.tvQuantityAtGetCart.setText(String.valueOf(cartProduct.getQuantity()));

        String imageURL = cartProduct.getProduct().getFeaturedImages().getFeaturedImageURL();

        Glide.with(mContext)
                .load(imageURL)
                .into(holder.ivProductImage);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.s1_mob1)                 ImageView ivProductImage;
        @BindView(R.id.mobileName)              TextView tvMobileName;
        @BindView(R.id.version)                 TextView tvMobilePrize;

        @BindView(R.id.move_to_whishlist)       Button btnMoveToWhishlist;
        @BindView(R.id.remove)                  Button btnRemove;
        @BindView(R.id.linear_layout_card)      LinearLayout linearLayout;
        @BindView(R.id.increase_at_getCart)     Button btnIncrease;
        @BindView(R.id.decrease_at_getCart)     Button btnDecrease;
        @BindView(R.id.tv_quantity_at_getCart)  TextView tvQuantityAtGetCart;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            btnMoveToWhishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartListener.onAddToWhishlit(getAdapterPosition());
                }
            });
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartListener.onRemoveFromCart(getAdapterPosition());
                }
            });
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartListener.onViewItem(view,getAdapterPosition());
                }
            });
            btnIncrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartListener.onIncreaseButtonClicked(tvQuantityAtGetCart,getAdapterPosition());
                }
            });
            btnDecrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartListener.onDecreaseButtonClicked(tvQuantityAtGetCart,getAdapterPosition());
                }
            });
        }
    }

    public interface CartListener {
        void onAddToWhishlit( int position);
        void onRemoveFromCart(int position);
        void onViewItem( View view,  int position);
        void onIncreaseButtonClicked(TextView view, int position);
        void onDecreaseButtonClicked(TextView view,int position);
        void onQuantityAtCart(TextView view, int num);
    }

}
