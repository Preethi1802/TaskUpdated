package com.example.admin.task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SingleItemActivity extends AppCompatActivity {

    private static final String TAG = "SingleItemActivity";

    ImageView imageView, imageView1, imageView2, imageView3, imageView4;
    TextView mobName, mobVersion, mobPrize, mobRating, ratingInWords;
    ArrayList<Products> productList;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item_desc);

        imageView = (ImageView) findViewById(R.id.product1);
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

        productList = intent.getParcelableArrayListExtra(Constants.STORED_ITEMS);

        Log.i(TAG, "size" + productList.size());

        Log.i(TAG, "position" + position);

        imageView.setImageResource(productList.get(position).getImage());
        imageView1.setImageResource(productList.get(position).getImgView1());
        imageView2.setImageResource(productList.get(position).getImgView2());
        imageView3.setImageResource(productList.get(position).getImgView3());
        imageView4.setImageResource(productList.get(position).getImgView4());
        mobName.setText(productList.get(position).getMobileName());
        mobVersion.setText(productList.get(position).getVersion());
        mobPrize.setText(productList.get(position).getMobilePrize());
        mobRating.setText(productList.get(position).getMobileRating());
        ratingInWords.setText(productList.get(position).getRatingInWords());


    }

}
