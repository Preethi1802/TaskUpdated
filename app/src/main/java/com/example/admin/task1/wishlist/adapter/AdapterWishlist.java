package com.example.admin.task1.wishlist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.task1.R;
import com.example.admin.task1.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 9/15/2017.
 */

public class AdapterWishlist extends RecyclerView.Adapter<AdapterWishlist.ViewHolder>
{
    private static final String TAG = "AdapterWishlist";

    AdapterWishlist.WhishlistListener whishlistListener;

    List<Product> whishList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    public Context mContext;

    public AdapterWishlist(Context context, List<Product> whishList, AdapterWishlist.WhishlistListener whishlistListener) {
        layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.whishList = whishList;
        this.mContext = context;
        this.whishlistListener = whishlistListener;

    }

    @Override
    public AdapterWishlist.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.wishlist_item, null);
        AdapterWishlist.ViewHolder viewHolder = new AdapterWishlist.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterWishlist.ViewHolder holder, final int position) {

        Product whishListProduct = whishList.get(position);
        holder.tvMobileName.setText(whishListProduct.getName());
        holder.tvMobilePrize.setText(whishListProduct.getRegularPrice());
        Log.i(TAG, "%%%%%%%%%%%%%%%%%%" + whishListProduct.getFeaturedImages());

        String imageURL = whishListProduct.getFeaturedImages().getFeaturedImageURL();

        Glide.with(mContext)
                .load(imageURL)
                .into(holder.ivProductImage);
    }

    @Override
    public int getItemCount() {
        return whishList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.s1_mob1)                 ImageView ivProductImage;
        @BindView(R.id.mobileName)              TextView tvMobileName;
        @BindView(R.id.version)                 TextView tvMobilePrize;

        @BindView(R.id.remove_whishlist)        ImageView ivRemoveWhishlist;
        @BindView(R.id.linear_layout_whishlist) LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            ivRemoveWhishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    whishlistListener.onRemoveWhishlist(getAdapterPosition());
                }
            });
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    whishlistListener.onViewItem(view,getAdapterPosition());
                }
            });
        }
    }

    public interface WhishlistListener {
        void onRemoveWhishlist(int position);
        void onViewItem( View view,  int position);
    }
}
