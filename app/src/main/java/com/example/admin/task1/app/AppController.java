package com.example.admin.task1.app;

import android.app.Application;
import android.support.annotation.StringRes;

import com.example.admin.task1.utilities.SessionManager;

import java.io.File;
import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AppController extends Application {

    private static AppController sInstance;

    SessionManager sessionManager;

    private AppController() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sessionManager= new SessionManager(this);
        final OkHttpClient client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        return response.request().newBuilder()
                                .addHeader("Authentication", "Bearer "+SessionManager.getInstance().getAPIToken())
                               // .addHeader("X-AUTH-TOKEN", SessionManager.getInstance().getAPIToken())
                                .build();
                    }
                })
                .build();
    }

    public static void createCacheFolder() {
        File cacheDir;
      /*  if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), AppUtil.DEFAULT_CACHE_DIR+"/cache");
        else
            cacheDir = AppController.getInstance().getCacheDir();
        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }*/
    }

    public static String getStr(@StringRes int id){
        return sInstance.getString(id);
    }

    public static synchronized AppController getInstance() {
        return sInstance;
    }

}