package com.ocr.navigation.retrofit;


import com.ocr.navigation.OOP.ResponePost;

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

    @GET("getSignIn/readGetUser.php")
    Call<GetUserResponse> getListUser();

    @POST("dangky.php")
    @FormUrlEncoded
    Call<SignUpResponse> dangky(
            @Field("username") String username,
            @Field("gender") String gender,
            @Field("dateofbirth") String dateofbirth,
            @Field("phoneNumber") String phonenumber,
            @Field("address") String address,
            @Field("city") String city,
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("donhang.php")
    @FormUrlEncoded
    Call<ResponePost> createOder(
            @Field("user_id")int user_id,
            @Field("soluong") int soluong,
            @Field("total_money") int total_money,
            @Field("status") String status,
            @Field("payment_methods") String payment_methods,
            @Field("note") String note,
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("diachi") String diachi,
            @Field("created_date") String created_date,
            @Field("chitiet") String chitiet
    );

}


