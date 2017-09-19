package com.example.admin.task1.api.remote;

import android.support.annotation.NonNull;

import com.example.admin.task1.BuildConfig;
import com.example.admin.task1.utilities.SessionManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(final String baseUrl) {
        if (retrofit == null) {

            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    HttpUrl.Builder builder = chain.request().url().newBuilder();
                    HttpUrl newUrl = builder
                           // .host(baseUrl)
                            .build();
                    final Request request = chain.request().newBuilder()
                            .url(newUrl)
                            .addHeader("Authentication", "Bearer "+SessionManager.getInstance().getAPIToken())
                           // .addHeader("X-AUTH-TOKEN", SessionManager.getInstance().getAPIToken())
                            .build();
                    return chain.proceed(request);
                }
            };

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor);

            if (BuildConfig.DEBUG) {
                APILogger logger  = new APILogger();
                logger.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(logger);
            }

            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
