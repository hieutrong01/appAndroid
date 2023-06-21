package com.ocr.navigation.retrofit.com.ocr.navigation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();
//
//    ApiInterface apiInterface = new Retrofit.Builder()
//            .baseUrl("http://192.168.1.13/DIOR/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build()
//                    .create(ApiInterface.class);

    @GET("read.php")
    Call<GetUserResponse> getListUser();

    @POST("dangky.php")
    @FormUrlEncoded
    Call<SignUpResponse> dangky(
            @Field("username") String username,
            @Field("phoneNumber") String phonenumber,
            @Field("address") String address,
            @Field("city") String city,
            @Field("email") String email,
            @Field("password") String password
    );
}


