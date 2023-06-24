package com.ocr.navigation.retrofit;

import com.google.gson.GsonBuilder;
import com.ocr.navigation.retrofit.com.ocr.navigation.ApiInterface;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.1.80/DIOR/";

    private static Retrofit instance;
    private static ApiInterface apiInterface;

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder()
                                    .setLenient()
                                    .create()
                    ))
                    .build();
        }
        return instance;
    }

    public static ApiInterface getApi() {
        if (apiInterface == null) {
            apiInterface = getInstance().create(ApiInterface.class);
        }
        return apiInterface;
    }
}
