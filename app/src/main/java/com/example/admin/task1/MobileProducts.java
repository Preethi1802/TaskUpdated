package com.example.admin.task1;

import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Admin on 7/26/2017.
 */

public class MobileProducts extends AppCompatActivity {
    Toolbar toolbar;

    RecyclerView recyclerView;
    AdapterForProducts adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] mobileName, version;
    int[] image ={R.drawable.s1_mob1,R.drawable.s1_mob2,R.drawable.s1_mob3,R.drawable.s1_mob4};


    String[] mobilePrize, mobileRating, ratingInwords;
    int[] imgView1 ={R.drawable.lenova2,R.drawable.fierce_xl_view1,R.drawable.iphone_view1,R.drawable.lg_view1};
    int[] imgView2 ={R.drawable.lenova3,R.drawable.fierce_xl_view2,R.drawable.iphone_view2,R.drawable.lg_view2};
    int[] imgView3 ={R.drawable.lenova4,R.drawable.fierce_xl_view3,R.drawable.iphone_view3,R.drawable.lg_view3,};
    int[] imgView4 ={R.drawable.lenova5,R.drawable.fierce_xl_view4,R.drawable.iphone_view4,R.drawable.lg_view4};

    ArrayList<Products> arrayList = new ArrayList<Products>();

    GridLayoutManager gridLayoutManager;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.s2_activity );

        toolbar =(Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mobiles");

        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }


        mobileName = getResources().getStringArray(R.array.MobileName);
        version = getResources().getStringArray(R.array.Version);
        mobilePrize = getResources().getStringArray(R.array.mobilePrize);
        mobileRating = getResources().getStringArray(R.array.mobileRating);
        ratingInwords= getResources().getStringArray(R.array.ratingInwords);

        int i = 0;
        for(String name : mobileName)
        {
            Products products = new Products(name , version[i], mobilePrize[i],mobileRating[i],ratingInwords[i], image[i],imgView1[i],imgView2[i],imgView3[i],imgView4[i]);
            arrayList.add(products);
            i++;
        }

        recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        layoutManager = new GridLayoutManager(MobileProducts.this , 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new AdapterForProducts(this,arrayList);
        recyclerView.setAdapter(adapter);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
