package com.example.recyclerviewproject.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    public static String BASE_URL = "https://s3-us-west-2.amazonaws.com/appsdeveloperblog.com/tutorials/files/";

    public static Retrofit retrofit;

    public static Retrofit getRetrofitContent() {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.readTimeout(60, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = okHttpClient.build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }

    public static ApiService initializeApiService() {
        ApiService apiService = RetroInstance.getRetrofitContent().create(ApiService.class);
        return apiService;
    }

}
