package com.example.admin.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemDesc extends AppCompatActivity
{
    ImageView imageView, imageView1,imageView2, imageView3, imageView4;
    TextView mobName,mobVersion ,mobPrize,mobRating,ratingInWords;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_desc);

        imageView = (ImageView)findViewById(R.id.product1);
        imageView1= (ImageView)findViewById(R.id.view1);
        imageView2= (ImageView)findViewById(R.id.view2);
        imageView3= (ImageView)findViewById(R.id.view3);
        imageView4= (ImageView)findViewById(R.id.view4);
        mobName =(TextView)findViewById(R.id.productName1);
        mobVersion = (TextView)findViewById(R.id.modelName);
        mobPrize =(TextView)findViewById(R.id.prize);
        mobRating =(TextView)findViewById(R.id.rating);
        ratingInWords =(TextView)findViewById(R.id.ratingInWords);

        imageView.setImageResource(getIntent().getIntExtra("imageView",00));
        imageView1.setImageResource(getIntent().getIntExtra("imageview1",11));
        imageView2.setImageResource(getIntent().getIntExtra("imageview2",22));
        imageView3.setImageResource(getIntent().getIntExtra("imageview3",33));
        imageView4.setImageResource(getIntent().getIntExtra("imageview4",44));
        mobName.setText(getIntent().getStringExtra("textMob1"));
        mobVersion.setText(getIntent().getStringExtra("version"));
        mobPrize.setText(getIntent().getStringExtra("mobPrize"));
        mobRating.setText(getIntent().getStringExtra("mobRating"));
        ratingInWords.setText(getIntent().getStringExtra("ratingInWords"));

    }
}
