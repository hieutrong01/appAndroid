package com.ocr.navigation.my_interface;

import com.ocr.navigation.OOP.Product;

import java.util.List;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIService {
    //http://localhost/uniquilo/getsanpham.php

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel( HttpLoggingInterceptor.Level.BODY );

    OkHttpClient.Builder okBuilder= new OkHttpClient.Builder()
            .readTimeout( 30, TimeUnit.SECONDS )
            .connectTimeout( 30,TimeUnit.SECONDS )
            .retryOnConnectionFailure( true )
            .addInterceptor( loggingInterceptor );

    APIService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.2.5/uniquilo/")
            .addConverterFactory( GsonConverterFactory.create() )
            .addCallAdapterFactory( RxJava3CallAdapterFactory.create() )
            .client( okBuilder.build() )
            .build()
            .create( APIService.class );


    @GET("getsanpham.php")
    Observable<List<Product>> callAPIAoNam();
}
