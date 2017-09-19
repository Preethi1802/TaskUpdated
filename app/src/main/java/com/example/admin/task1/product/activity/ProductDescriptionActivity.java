package com.example.admin.task1.product.activity;

import android.content.Context;
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
import com.example.admin.task1.api.request.CartRequest;
import com.example.admin.task1.api.request.WishlistRequest;
import com.example.admin.task1.api.response.CartPostAddResponse;
import com.example.admin.task1.api.response.WishListPostAddResponse;
import com.example.admin.task1.api.response.CartGetResponse;
import com.example.admin.task1.api.response.WishListGetResponse;
import com.example.admin.task1.api.response.CartPostRemoveResponse;
import com.example.admin.task1.api.response.WishListPostRemoveResponse;
import com.example.admin.task1.api.subscriber.CartEventSubscriber;
import com.example.admin.task1.api.subscriber.WishlistProductEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.api.util.Constants;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.cart.activity.CartActivity;
import com.example.admin.task1.home.AllDetailsActivity;
import com.example.admin.task1.login.LoginActivity;
import com.example.admin.task1.model.Product;
import com.example.admin.task1.model.User;
import com.example.admin.task1.utilities.SessionManager;
import com.google.gson.Gson;
import com.thapovan.android.commonutils.text.TextUtil;
import com.thapovan.android.commonutils.toast.ToastUtil;
import com.thapovan.android.customui.TouchImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDescriptionActivity extends AppActivity implements CartEventSubscriber, WishlistProductEventSubscriber {

    @BindView(R.id.toolAction)
    Toolbar toolbar;
    @BindView(R.id.product1)
    TouchImageView ivFeaturedImage;
    @BindView(R.id.productName1)
    TextView tvMobName;
    @BindView(R.id.modelName)
    TextView tvMobVersion;
    @BindView(R.id.prize)
    TextView tvMobPrize;
    @BindView(R.id.rating)
    TextView tvMobRating;
    @BindView(R.id.ratingInWords)
    TextView tvRatingInWords;
    @BindView(R.id.allDetails)
    TextView tvAllDetails;
    @BindView(R.id.linear_layout_gallery)
    LinearLayout galleryLayout;
    @BindView(R.id.tv_quantity)
    TextView tvQuantity;
    @BindView(R.id.btn_add_to_cart)
    Button btnAddToCart;


    private ProductDescriptionActivity mActivity;

    private static final String TAG = ProductDescriptionActivity.class.getSimpleName();

    private ArrayList<Product> productList = new ArrayList<Product>();

    int position;
    SessionManager session;
    Gson gson;
    int mQuantity = 1;

    public static void start(Context context, Product product) {
        Intent starter = new Intent(context, ProductDescriptionActivity.class);
        starter.putExtra(Constants.KEY_EXTRA_PRODUCT, product);
        context.startActivity(starter);
    }

    private Product getProduct() {
        return getIntent().getParcelableExtra(Constants.KEY_EXTRA_PRODUCT);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
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


        String imageUrl = getProduct().getFeaturedImages().getFeaturedImageURL();

        Glide.with(this)
                .load(imageUrl)
                .into(ivFeaturedImage);

        for (int k = 0; k < getProduct().getGalleryImages().size(); k++) {

            final ImageView ivGalleryImage = new ImageView(this);
            ivGalleryImage.setId(k);
            ivGalleryImage.setLayoutParams(new LinearLayout.LayoutParams(220, 220));
            ivGalleryImage.setPadding(15, 15, 15, 15);
            ivGalleryImage.setBackgroundResource(R.drawable.image_border);

            final String imageUrl1 = getProduct().getGalleryImages().get(k).getGalleryImageURL();

            Glide.with(this)
                    .load(imageUrl1)
                    .into(ivGalleryImage);

            galleryLayout.addView(ivGalleryImage);

            ivGalleryImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivFeaturedImage.setImageDrawable(ivGalleryImage.getDrawable());
                }
            });
        }

        tvMobName.setText(getProduct().getName());
        tvMobVersion.setText(Html.fromHtml(getProduct().getSpec()).toString());
        tvMobPrize.setText(getProduct().getRegularPrice());

    }

    @OnClick(R.id.increase)
    public void increaseInt(View view) {
        cart();
        mQuantity = mQuantity + 1;
        display(mQuantity);
    }

    @OnClick(R.id.decrease)
    public void decreaseInt(View view) {
        if (mQuantity <= 1) {
        } else {
            cart();
            mQuantity = mQuantity - 1;
            display(mQuantity);
        }

    }

    private void display(int num) {
        tvQuantity.setText(String.valueOf(num));
    }

    @OnClick(R.id.btn_add_to_cart)
    public void onAddCartClicked() {
        cart();
    }

    public void cart()
    {
        if (session.isLoggedIn()) {
            User user = gson.fromJson(session.getUserObject(), User.class);

            CartRequest cartRequest = new CartRequest();
            cartRequest.setUser_id(user.getId());
            cartRequest.setProduct_id(getProduct().getId());
            cartRequest.setQuantity(TextUtil.cleanupString(tvQuantity.getText().toString().trim()));

            Log.i(TAG, "" + user.getId());
            showProgress();
            CommunicationManager.getInstance().postAddCart(cartRequest, mActivity);

        } else {
            ToastUtil.showCenterToast(getApplicationContext(), "Login or SignUp to Continue");
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @OnClick(R.id.allDetails)
    public void onAllDetailsClicked(View v) {
        Intent intent = new Intent(v.getContext(), AllDetailsActivity.class);
        intent.putExtra(Constants.KEY_POSITION, position);
        intent.putParcelableArrayListExtra(Constants.STORED_ITEMS, productList);
        v.getContext().startActivity(intent);
    }

    @OnClick(R.id.favourite_icon)
    public void onFavouriteIconClicked() {
        if (session.isLoggedIn()) {


            User user = gson.fromJson(session.getUserObject(), User.class);

            WishlistRequest wishlistRequest = new WishlistRequest();
            wishlistRequest.setUser_id(user.getId());
            wishlistRequest.setProduct_id(getProduct().getId());

            Log.i(TAG, "" + user.getId());

            showProgress();
            CommunicationManager.getInstance().postAddProductsToWhishlist(wishlistRequest, mActivity);

        } else {
            ToastUtil.showCenterToast(getApplicationContext(), "Login or SignUp to Continue");
            Intent intent = new Intent(mActivity, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        if (id == R.id.cart_toolbar) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetCartCompleted(CartGetResponse cartGetResponse) {

    }

    @Override
    public void onAddCartCompleted(CartPostAddResponse cartPostAddResponse) {

        hideProgress();
        if (cartPostAddResponse.isSuccess()) {
            btnAddToCart.setText("GO TO CART");
            ToastUtil.showCenterToast(getApplicationContext(), cartPostAddResponse.getMessage());

        } else {
            ToastUtil.showCenterToast(getApplicationContext(), cartPostAddResponse.getMessage());
        }

    }

    @Override
    public void onRemoveCartCompleted(CartPostRemoveResponse cartPostRemoveResponse) {

    }


    @Override
    public void onGetWhishlistCompleted(WishListGetResponse wishListGetResponse) {

    }

    @Override
    public void onAddWishListCompleted(WishListPostAddResponse wishListPostAddResponse) {
        hideProgress();
        if (wishListPostAddResponse.isSuccess()) {
            ToastUtil.showCenterToast(mActivity, wishListPostAddResponse.getMessage());

        } else {
            ToastUtil.showCenterToast(getApplicationContext(), wishListPostAddResponse.getMessage());
        }
    }

    @Override
    public void onRemoveWishListCompleted(WishListPostRemoveResponse wishListPostRemoveResponse) {

    }
}
