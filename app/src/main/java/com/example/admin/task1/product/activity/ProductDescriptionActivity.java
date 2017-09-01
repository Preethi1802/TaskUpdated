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
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.Product;
import com.thapovan.android.customui.TouchImageView;

import java.util.ArrayList;

public class ProductDescriptionActivity extends AppActivity {

    private static final String TAG = "ActivityProductDesc";
    TouchImageView iv_featuredImage;
    TextView tv_mobName, tv_mobVersion, tv_mobPrize, tv_mobRating, tv_ratingInWords, tv_allDetails;
    ArrayList<Product> productList = new ArrayList<Product>();

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_description);

        iv_featuredImage = (TouchImageView) findViewById(R.id.product1);
        tv_mobName = (TextView) findViewById(R.id.productName1);
        tv_mobVersion = (TextView) findViewById(R.id.modelName);
        tv_mobName = (TextView) findViewById(R.id.productName1);
        tv_mobVersion = (TextView) findViewById(R.id.modelName);
        tv_mobPrize = (TextView) findViewById(R.id.prize);
        tv_mobRating = (TextView) findViewById(R.id.rating);
        tv_ratingInWords = (TextView) findViewById(R.id.ratingInWords);
        tv_allDetails = (TextView) findViewById(R.id.allDetails);
        Intent intent = getIntent();
        Log.i(TAG, "hiiiiii");

        position = intent.getIntExtra(Constants.KEY_POSITION, 0);
        Log.i(TAG, "...............position........." + position);

        productList = intent.getParcelableArrayListExtra(Constants.STORED_ITEMS);
        Log.i(TAG, "..........size............." + productList.size());

        Log.i(TAG, ".......GalleryImage..........." + productList.get(position).getGalleryImages());

        String imageURL = productList.get(position).getFeaturedImages().getFeaturedImageURL();
        Glide.with(this).load(imageURL).into(iv_featuredImage);

        LinearLayout galleryLayout = (LinearLayout) findViewById(R.id.linear_layout_gallery);

        Log.i(TAG, "" + productList.get(position).getImages().size());

        for (int k = 0; k < productList.get(position).getGalleryImages().size(); k++) {
            final ImageView iv_galleryImage = new ImageView(this);
            iv_galleryImage.setId(k);
            iv_galleryImage.setLayoutParams(new LinearLayout.LayoutParams(250, 200));
            final String imageURL1 = productList.get(position).getGalleryImages().get(k).getGalleryImageURL();

            Log.i(TAG, "image Url" + imageURL1);

            Glide.with(this).load(imageURL1).into(iv_galleryImage);

            iv_galleryImage.setScaleType(ImageView.ScaleType.FIT_XY);
            galleryLayout.addView(iv_galleryImage);

            iv_galleryImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //      Toast.makeText(getApplicationContext(), "ON ITEM" + imageURL1, Toast.LENGTH_LONG).show();
                    iv_featuredImage.setImageDrawable(iv_galleryImage.getDrawable());
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
        tv_mobName.setText(productList.get(position).getName());
        tv_mobVersion.setText(productList.get(position).getSpec());
        tv_mobPrize.setText(productList.get(position).getRegularPrice());
        tv_ratingInWords.setText(productList.get(position).getDescription());
    }

}
