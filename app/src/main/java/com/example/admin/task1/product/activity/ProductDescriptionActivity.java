package com.example.admin.task1.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.task1.R;
import com.example.admin.task1.activity.AllDetailsActivity;
import com.example.admin.task1.api.util.APIUtil;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.Product;
import com.thapovan.android.customui.TouchImageView;

import java.util.ArrayList;

public class ProductDescriptionActivity extends AppActivity {

    private static final String TAG = "ActivityProductDesc";
    TouchImageView imageView;
    TextView mobName, mobVersion, mobPrize, mobRating, ratingInWords, tv_allDetails;
    ArrayList<Product> productList = new ArrayList<Product>();

    String name, path;
    String imageUrl = "http://192.168.1.73/projects/learning/laravel/e-commerce-portal/";

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_description);

        imageView = (TouchImageView) findViewById(R.id.product1);
        mobName = (TextView) findViewById(R.id.productName1);
        mobVersion = (TextView) findViewById(R.id.modelName);
        mobName = (TextView) findViewById(R.id.productName1);
        mobVersion = (TextView) findViewById(R.id.modelName);
        mobPrize = (TextView) findViewById(R.id.prize);
        mobRating = (TextView) findViewById(R.id.rating);
        ratingInWords = (TextView) findViewById(R.id.ratingInWords);
        tv_allDetails = (TextView) findViewById(R.id.allDetails);
        Intent intent = getIntent();
        Log.i(TAG, "hiiiiii");

        position = intent.getIntExtra(APIUtil.KEY_POSITION, 0);
        Log.i(TAG, "...............position........." + position);

        productList = intent.getParcelableArrayListExtra(APIUtil.STORED_ITEMS);
        Log.i(TAG, "..........size............." + productList.size());

        Log.i(TAG, ".......GalleryImage..........." + productList.get(position).getGalleryImages());

        String imageURL = productList.get(position).getFeaturedImages().getFeaturedImageURL();
        Glide.with(this).load(imageURL).into(imageView);

        LinearLayout galleryLayout = (LinearLayout) findViewById(R.id.linear_layout_gallery);

        Log.i(TAG, "" + productList.get(position).getImages().size());

        for (int k = 0; k < productList.get(position).getGalleryImages().size(); k++) {
            final ImageView itemView = new ImageView(this);
            itemView.setId(k);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(250, 200));
            final String imageURL1 = productList.get(position).getGalleryImages().get(k).getGalleryImageURL();

            Log.i(TAG, "image Url" + imageURL1);

            Glide.with(this).load(imageURL1).into(itemView);

            itemView.setScaleType(ImageView.ScaleType.FIT_XY);
            galleryLayout.addView(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //      Toast.makeText(getApplicationContext(), "ON ITEM" + imageURL1, Toast.LENGTH_LONG).show();
                    imageView.setImageDrawable(itemView.getDrawable());
                }
            });

            tv_allDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AllDetailsActivity.class);
                    startActivity(intent);
                }
            });

        }
        mobName.setText(productList.get(position).getName());
        mobVersion.setText(productList.get(position).getSpec());
        mobPrize.setText(productList.get(position).getRegularPrice());
        ratingInWords.setText(productList.get(position).getDescription());
    }

}
