package com.ocr.navigation.my_interface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ocr.navigation.OOP.DataProduct;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIService {
    //http://localhost/uniquilo/getsanpham.php

    //http://localhost/uniquilo/getaonu/readAoNu.php

    Gson gson = new GsonBuilder().setDateFormat( "dd-MM-yyy" ).create();
    //https://jsonplaceholder.typicode.com/posts?userId=1
    //http://192.168.1.6/uniquilo/customers/read.php
    //
    APIService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.188.41/uniquilo/customers/")
            .addConverterFactory( GsonConverterFactory.create(gson) )
            .build()
            .create( APIService.class );

    APIService apiServiceNu = new Retrofit.Builder()
            .baseUrl("http://192.168.188.41/uniquilo/getaonu/")
            .addConverterFactory( GsonConverterFactory.create(gson) )
            .build()
            .create( APIService.class );

   // http://localhost/uniquilo/getkids/readao.php

    APIService apiServiceKids = new Retrofit.Builder()
            .baseUrl("http://192.168.188.41/uniquilo/getkids/")
            .addConverterFactory( GsonConverterFactory.create(gson) )
            .build()
            .create( APIService.class );

    @GET("read.php")
    Call<DataProduct> getLisAoMen();

    @GET("readAoNu.php")
    Call<DataProduct> getLisAoWomen();

    @GET("readao.php")
    Call<DataProduct> getLisAoKids();
}


