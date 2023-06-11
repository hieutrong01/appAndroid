package com.ocr.navigation.retrofit.com.ocr.navigation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiInterface{

//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();
//
//    ApiInterface apiInterface = new Retrofit.Builder()
//            .baseUrl("http://192.168.1.21/DIOR/userr/read.php")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build()
//                    .create(ApiInterface.class);

    @GET("userr/read.php")
    Call<GetUserResponse> getListUser();
}


