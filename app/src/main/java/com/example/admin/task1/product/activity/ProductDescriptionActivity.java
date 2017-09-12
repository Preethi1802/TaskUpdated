package com.example.admin.task1.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.task1.R;
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.home.AllDetailsActivity;
import com.example.admin.task1.model.Product;
import com.example.admin.task1.utilities.SessionManager;
import com.google.gson.Gson;
import com.thapovan.android.customui.TouchImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDescriptionActivity extends AppActivity {

    @BindView(R.id.toolAction)              Toolbar toolbar;
    @BindView(R.id.product1)                TouchImageView ivFeaturedImage;
    @BindView(R.id.productName1)            TextView tvMobName;
    @BindView(R.id.modelName)               TextView tvMobVersion;
    @BindView(R.id.prize)                   TextView tvMobPrize;
    @BindView(R.id.rating)                  TextView tvMobRating;
    @BindView(R.id.ratingInWords)           TextView tvRatingInWords;
    @BindView(R.id.allDetails)              TextView tvAllDetails;
    @BindView(R.id.linear_layout_gallery)   LinearLayout galleryLayout;

    @BindView(R.id.btn_add_to_cart)
    Button btnAddToCart;


    private static final String TAG = "ActivityProductDesc";
    ArrayList<Product> productList = new ArrayList<Product>();

    int position;
    SessionManager session;
    Gson gson;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_activity, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product_description);
        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());
        gson = new Gson();


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

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

        Log.i(TAG, "" + productList.get(position).getImages().size());

        for (int k = 0; k < productList.get(position).getGalleryImages().size(); k++) {

            final ImageView ivGalleryImage = new ImageView(this);
            ivGalleryImage.setId(k);
            ivGalleryImage.setLayoutParams(new LinearLayout.LayoutParams(220, 220));
            ivGalleryImage.setPadding(15, 15, 15, 15);
            ivGalleryImage.setBackgroundResource(R.drawable.image_border);


            final String imageURL1 = productList.get(position).getGalleryImages().get(k).getGalleryImageURL();

            Log.i(TAG, "image Url" + imageURL1);

            Glide.with(this)
                    .load(imageURL1)
                    .into(ivGalleryImage);

            galleryLayout.addView(ivGalleryImage);

            ivGalleryImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivFeaturedImage.setImageDrawable(ivGalleryImage.getDrawable());
                }
            });
        }
        tvMobName.setText(productList.get(position).getName());
        tvMobVersion.setText(Html.fromHtml(productList.get(position).getSpec()).toString());
        tvMobPrize.setText(productList.get(position).getRegularPrice());
//      tvRatingInWords.setText(Html.fromHtml(productList.get(position).getDescription()).toString());

//        if (session.getCartProducts().containsValue(productList.get(position).getId()))
//        {
//            ToastUtil.showCenterToast(getApplicationContext(),""+session.getCartProducts());
//            btnAddToCart.setText("Remove From Cart");
//        }
    }

    @OnClick(R.id.allDetails)
    public void AllDetails(View v) {
        Intent intent = new Intent(v.getContext(), AllDetailsActivity.class);
        intent.putExtra(Constants.KEY_POSITION, position);
        intent.putParcelableArrayListExtra(Constants.STORED_ITEMS, productList);
        v.getContext().startActivity(intent);
    }

    //********************************************************************************************
//    @OnClick(R.id.btn_add_to_cart)
//    public void onAddCartClicked() {
//        if (session.isLoggedIn()) {
//            session.createCart(productList.get(position).getId().toString());
//            ToastUtil.showCenterToast(getApplicationContext(), "Product added to cart"+session.getCartProducts().size());
//        } else {
//            ToastUtil.showCenterToast(getApplicationContext(), "Please login or signup to continue");
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//        }
//    }

    //********************************************************************************************
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
