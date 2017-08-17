package com.example.admin.task1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SingleItemActivity extends AppCompatActivity {

    private static final String TAG = "SingleItemActivity";

    ImageView imageView, imageView1, imageView2, imageView3, imageView4;
    TextView mobName, mobVersion, mobPrize, mobRating, ratingInWords;
    ArrayList<Products> productList = new ArrayList<Products>();

    int position;
    String url,urlView1,urlView2,urlView3,urlView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item_desc);

        imageView = (ImageView) findViewById(R.id.product1);
        mobName = (TextView) findViewById(R.id.productName1);
        mobVersion = (TextView) findViewById(R.id.modelName);
        imageView1 = (ImageView) findViewById(R.id.view1);
        imageView2 = (ImageView) findViewById(R.id.view2);
        imageView3 = (ImageView) findViewById(R.id.view3);
        imageView4 = (ImageView) findViewById(R.id.view4);
        mobName = (TextView) findViewById(R.id.productName1);
        mobVersion = (TextView) findViewById(R.id.modelName);
        mobPrize = (TextView) findViewById(R.id.prize);
        mobRating = (TextView) findViewById(R.id.rating);
        ratingInWords = (TextView) findViewById(R.id.ratingInWords);

        Intent intent = getIntent();
        Log.i(TAG, "hiiiiii");

        position = intent.getIntExtra(Constants.KEY_POSITION, 0);
        Log.i(TAG, "position" + position);

        productList = intent.getParcelableArrayListExtra(Constants.STORED_ITEMS);
        Log.i(TAG, "size" + productList.size());

        url = productList.get(position).getURL();
        Glide.with(this).load(url).into(imageView);
        urlView1 = productList.get(position).getUrlView1();
        urlView2 = productList.get(position).getUrlView2();
        urlView3 = productList.get(position).getUrlView3();
        urlView4 = productList.get(position).getUrlView4();
        Glide.with(this).load(urlView1).into(imageView1);
        Glide.with(this).load(urlView2).into(imageView2);
        Glide.with(this).load(urlView3).into(imageView3);
        Glide.with(this).load(urlView4).into(imageView4);
        mobName.setText(productList.get(position).getMobileName());
        mobVersion.setText(productList.get(position).getVersion());
        mobPrize.setText(productList.get(position).getMobilePrize());
        mobRating.setText(productList.get(position).getMobileRating());
        ratingInWords.setText(productList.get(position).getRatingInWords());

    }

}
