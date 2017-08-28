package com.example.admin.task1.adapter;

/**
 * Created by Admin on 7/26/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.task1.R;
import com.example.admin.task1.activity.ActivityProductDescription;
import com.example.admin.task1.api.remote.Constants;
import com.example.admin.task1.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    private static final String TAG = "AdapterProduct";
    List<Product> list= new ArrayList<>();
    public Context mContext;

    public AdapterProduct(Context applicationContext, ArrayList<Product> list) {
        this.list = list;
        this.mContext = applicationContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mContext, list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        Product product = list.get(position);
        holder.mobileName.setText(product.getName());
        holder.version.setText(product.getSpec());
        Log.i(TAG,"%%%%%%%%%%%%%%%%%%"+product.getFeaturedImages());

        String imageURL= product.getFeaturedImages().getFeaturedImageURL();

                Glide.with(mContext)
                        .load(imageURL)
                        .into(holder.productImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, Constants.STORED_ITEMS + position);

                Intent intent = new Intent(v.getContext(), ActivityProductDescription.class);
                intent.putExtra(Constants.KEY_POSITION, position);
                intent.putParcelableArrayListExtra(Constants.STORED_ITEMS, (ArrayList<? extends Parcelable>) list);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView mobileName, version;
        List<Product> items;
        Context ctx;

        public ViewHolder(View itemView, Context ctx, List<Product> items) {
            super(itemView);
            this.items = items;
            this.ctx = ctx;
            productImage = (ImageView) itemView.findViewById(R.id.s1_mob1);
            mobileName = (TextView) itemView.findViewById(R.id.mobileName);
            version = (TextView) itemView.findViewById(R.id.version);

        }


    }


}
