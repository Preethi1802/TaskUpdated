package com.example.admin.task1;

/**
 * Created by Admin on 7/26/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MobileProductsAdapter extends RecyclerView.Adapter<MobileProductsAdapter.ViewHolder>
{
    private ArrayList<MobileItems> itemList =new ArrayList<MobileItems>();
    Context mCtx;

    public MobileProductsAdapter(Context mCtx, ArrayList<MobileItems> itemList) {
        this.itemList= itemList;
        this.mCtx=mCtx;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent ,false);
        ViewHolder viewHolder= new ViewHolder(view, mCtx, itemList);

        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        MobileItems mobileItems = itemList.get(position);
        holder.imageView.setImageResource(mobileItems.getImage());
        holder.textMob1.setText(mobileItems.getMobileName());
        holder.version.setText(mobileItems.getVersion());

    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        TextView textMob1,version;

        ImageView imageView1,imageView2,imageView3,imageView4;
        TextView mobPrize, mobRating, ratingInWords;

        ArrayList<MobileItems> items = new ArrayList<MobileItems>();
         Context ctx ;
        public ViewHolder(View itemView, Context ctx, ArrayList<MobileItems> items)
        {
            super(itemView);
            this.items = items;
            this.ctx= ctx;
            itemView.setOnClickListener(this);
            imageView = (ImageView)itemView.findViewById(R.id.s1_mob1);
            textMob1 = (TextView)itemView.findViewById(R.id.card_text1);
            version = (TextView)itemView.findViewById(R.id.version);
            imageView1 =(ImageView)itemView.findViewById(R.id.view1);
            imageView2 =(ImageView)itemView.findViewById(R.id.view2);
            imageView3=(ImageView)itemView.findViewById(R.id.view3);
            imageView4 =(ImageView)itemView.findViewById(R.id.view4);
            mobPrize =(TextView)itemView.findViewById(R.id.prize);
            mobRating =(TextView)itemView.findViewById(R.id.rating);
            ratingInWords =(TextView)itemView.findViewById(R.id.ratingInWords);
        }

        @Override
        public void onClick(View v)
        {
            int position = getAdapterPosition();
            MobileItems items = this.items.get(position);
            Intent intent= new Intent(this.ctx, SingleItemDesc.class);
            intent.putExtra("imageView",items.getImage());
            intent.putExtra("textMob1",items.getMobileName());
            intent.putExtra("version",items.getVersion());
            intent.putExtra("imageview1",items.getImgView1());
            intent.putExtra("imageview2",items.getImgView2());
            intent.putExtra("imageview3", items.getImgView3());
            intent.putExtra("imageview4", items.getImgView4());
            intent.putExtra("mobPrize",items.getMobilePrize());
            intent.putExtra("mobRating",items.getMobileRating());
            intent.putExtra("ratingInWords",items.getRatingInWords());
            this.ctx.startActivity(intent);

        }
    }

}
