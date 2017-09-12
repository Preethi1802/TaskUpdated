package com.example.admin.task1.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.admin.task1.home.MainActivity;
import com.example.admin.task1.login.LoginActivity;
import com.example.admin.task1.product.activity.ProductDescriptionActivity;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by Admin on 9/7/2017.
 */

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context mContext;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "FlipkartPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_OBJECT = "myObject";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_CARTID = "id";


    Gson gson;

    // Constructor
    public SessionManager(Context context){
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        gson= new Gson();
    }

    // Create login session
    public void createLoginSession(/*String name, String email,*/ Object object){
        // Storing login value as TRUE
        String json = gson.toJson(object);
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_OBJECT,json);

       /* // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);*/

        // commit changes
        editor.commit();
    }

    public String getUserObject()
    {
        String user = pref.getString(KEY_OBJECT, "");
        return user;
    }

    // Get stored session data in key and value pair
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }



    // Check login method wil check user login status ,If false it will redirect user to login page
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(mContext, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            mContext.startActivity(i);
        }
    }
    // log out user and Clear session details
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(mContext, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mContext.startActivity(i);
    }

    // Get Login State , Quick check for login
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void createCart(String id) {
        // Storing product to cart
        editor.putBoolean(IS_LOGIN, true);
         // Storing id in pref
        editor.putString(KEY_CARTID, id);
        editor.commit();

    }

    public HashMap<String, String> getCartProducts(){
        HashMap<String, String> cartProducts = new HashMap<String, String>();
        // user name
        cartProducts.put(KEY_NAME, pref.getString(KEY_CARTID, null));

        return cartProducts;
    }

    public void cartClear(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(mContext, ProductDescriptionActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mContext.startActivity(i);
    }
}
