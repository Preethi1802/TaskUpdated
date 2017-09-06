package com.example.admin.task1.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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
    TouchImageView ivFeaturedImage;
    TextView tvMobName, tvMobVersion, tvMobPrize, tvMobRating, tvRatingInWords, tvAllDetails;
    ArrayList<Product> productList = new ArrayList<Product>();

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_description);

        ivFeaturedImage = (TouchImageView) findViewById(R.id.product1);
        tvMobName = (TextView) findViewById(R.id.productName1);
        tvMobVersion = (TextView) findViewById(R.id.modelName);
        tvMobName = (TextView) findViewById(R.id.productName1);
        tvMobVersion = (TextView) findViewById(R.id.modelName);
        tvMobPrize = (TextView) findViewById(R.id.prize);
        tvMobRating = (TextView) findViewById(R.id.rating);
        tvRatingInWords = (TextView) findViewById(R.id.ratingInWords);
        tvAllDetails = (TextView) findViewById(R.id.allDetails);
        Intent intent = getIntent();
        Log.i(TAG, "hiiiiii");

        position = intent.getIntExtra(Constants.KEY_POSITION, 0);
        Log.i(TAG, "...............position........." + position);

        productList = intent.getParcelableArrayListExtra(Constants.STORED_ITEMS);
        Log.i(TAG, "..........size............." + productList.size());

        Log.i(TAG, ".......GalleryImage..........." + productList.get(position).getGalleryImages());

        String imageURL = productList.get(position).getFeaturedImages().getFeaturedImageURL();

        Glide.with(this)
                .load(imageURL)
                .into(ivFeaturedImage);

        LinearLayout galleryLayout = (LinearLayout) findViewById(R.id.linear_layout_gallery);

        Log.i(TAG, "" + productList.get(position).getImages().size());

        for (int k = 0; k < productList.get(position).getGalleryImages().size(); k++) {

            final ImageView ivGalleryImage = new ImageView(this);
            ivGalleryImage.setId(k);
            ivGalleryImage.setLayoutParams(new LinearLayout.LayoutParams(220, 220));
            ivGalleryImage.setPadding(15,15,15,15);
            ivGalleryImage.setBackgroundResource(R.drawable.image_border);


            final String imageURL1 = productList.get(position).getGalleryImages().get(k).getGalleryImageURL();

            Log.i(TAG, "image Url" + imageURL1);

            Glide.with(this)
                    .load(imageURL1)
                    .into(ivGalleryImage);

         //   ivGalleryImage.setScaleType(ImageView.ScaleType.FIT_XY);
            galleryLayout.addView(ivGalleryImage);

            ivGalleryImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplicationContext(), "ON ITEM" + imageURL1, Toast.LENGTH_LONG).show();
                    ivFeaturedImage.setImageDrawable(ivGalleryImage.getDrawable());
                }
            });

            tvAllDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), AllDetailsActivity.class);
                    intent.putExtra(Constants.KEY_POSITION, position);
                    intent.putParcelableArrayListExtra(Constants.STORED_ITEMS, productList);
                    v.getContext().startActivity(intent);
                }
            });

        }
        tvMobName.setText(productList.get(position).getName());
        tvMobVersion.setText(Html.fromHtml(productList.get(position).getSpec()).toString());
        tvMobPrize.setText(productList.get(position).getRegularPrice());
    //    tvRatingInWords.setText(Html.fromHtml(productList.get(position).getDescription()).toString());
    }

}
