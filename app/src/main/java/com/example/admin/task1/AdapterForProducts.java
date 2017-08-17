package com.example.admin.task1;

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

import java.util.ArrayList;
import java.util.List;

public class AdapterForProducts extends RecyclerView.Adapter<AdapterForProducts.ViewHolder> {
    private static final String TAG = "AdapterForProducts";
    List<Products> list;
    Context mContext;


    public AdapterForProducts(Context applicationContext, ArrayList<Products> list) {
        this.list = list;
        this.mContext = applicationContext;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mContext, list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        Products products = list.get(position);
        holder.mobileName.setText(products.getMobileName());
        holder.version.setText(products.getVersion());
        String image = products.getURL();

        Glide.with(mContext).load(image).into(holder.productImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, Constants.STORED_ITEMS + position);

                Intent intent = new Intent(v.getContext(), SingleItemActivity.class);
                intent.putExtra(Constants.KEY_POSITION, position);
                intent.putParcelableArrayListExtra(Constants.STORED_ITEMS, (ArrayList<? extends Parcelable>) list);
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

        List<Products> items;
        Context ctx;

        public ViewHolder(View itemView, Context ctx, List<Products> items) {
            super(itemView);
            this.items = items;
            productImage = (ImageView) itemView.findViewById(R.id.s1_mob1);
            mobileName = (TextView) itemView.findViewById(R.id.mobileName);
            version = (TextView) itemView.findViewById(R.id.version);

        }


    }


}
