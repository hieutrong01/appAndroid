package com.ocr.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ocr.navigation.retrofit.com.ocr.navigation.ApiInterface;
import com.ocr.navigation.retrofit.com.ocr.navigation.GetUserResponse;
import com.ocr.navigation.retrofit.com.ocr.navigation.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {
    private LinearLayout layoutSignUp;
    private EditText edtEmailSignIn, edtPasswordSignIn;
    private Button btnSignIn;
//    private TextView textViewResult;

    private List<User> mListUser;
    private User mUser;

    private static final String BASE_URL = "http://192.168.1.36/DIOR/";
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_in );
        iniUi();
//        initListener();

//      Khởi tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//      Tạo đối tượng ApiInterface từ Retrofit
        apiInterface = retrofit.create(ApiInterface.class);
        getListUser();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });

    }

    private void iniUi(){
        layoutSignUp =findViewById( R.id.layout_sign_up );
        edtEmailSignIn=findViewById( R.id.edt_email_in );
        edtPasswordSignIn=findViewById( R.id.edt_password_in );
        btnSignIn=findViewById( R.id.btn_sign_in );
//        textViewResult = findViewById(R.id.text_view_result);

    }

    private void clickLogin(){
        String strEmail = edtEmailSignIn.getText().toString().trim();
        String strPassword = edtPasswordSignIn.getText().toString().trim();

        if (mListUser == null || mListUser.isEmpty()){
            return;
        }

        boolean isHasUser = false;
        for (User user: mListUser){
            if (strEmail.equals(user.getEmail()) && strPassword.equals(user.getPassword())){
                isHasUser = true;
                mUser = user;
                break;
            }
        }
        if (isHasUser){
            //MainActivity
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Object_user", mUser);
            intent.putExtras(bundle);
            startActivity(intent);

        }
        else {
            Toast.makeText(SignInActivity.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }
    }

    private  void getListUser(){
        apiInterface.getListUser()
                .enqueue(new Callback<GetUserResponse>() {
                    @Override
                    public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                        Log.d("check", "onResponse: message = "+response.message() +"  body=" +response.body().toString());
                        if (response.isSuccessful()) {
                            mListUser = response.body().getData();
                            Log.d("List User", mListUser.size() + "");
                        } else {
                            Toast.makeText(SignInActivity.this, "Failed to get user list", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetUserResponse> call, Throwable t) {
                        t.printStackTrace();

                        Toast.makeText(SignInActivity.this, "Failed to connect to API", Toast.LENGTH_SHORT).show();
                    }
                });
    }


//    private void  initListener(){
//        layoutSignUp.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
//                startActivity( intent );
//            }
//        } );
//        btnSignIn.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                listClickSignUp();
//            }
//        } );
//    }
//
//    private void listClickSignUp() {
//        String strEmail = edtEmailSignIn.getText().toString().trim();
//        String strPassword = edtPasswordSignIn.getText().toString().trim();
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//         if (TextUtils.isEmpty( strEmail )){
//            Toast.makeText( this, "Vui lòng nhập Email", Toast.LENGTH_SHORT ).show();
//            return;
//        }
//        if (TextUtils.isEmpty( strPassword )){
//            Toast.makeText( this, "Vui lòng nhập Password", Toast.LENGTH_SHORT ).show();
//            return;
//        }
//            mAuth.signInWithEmailAndPassword( strEmail, strPassword )
//                    .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
////                            Toast.makeText(SignUpActivity.this, "Bạn đã đăng ký thành côngcông ",
////                                    Toast.LENGTH_SHORT).show();
//                                // Sign in success, update UI with the signed-in user's information
//                                Intent intent = new Intent( SignInActivity.this, MainActivity.class );
//                                startActivity( intent );
//
//                                finishAffinity();
//
//
//                            } else {
//                                // If sign in fails, display a message to the user.
//
//                                Toast.makeText( SignInActivity.this, "Bạn nhập sai tài khoản hoặc mật khẩu.",
//                                        Toast.LENGTH_SHORT ).show();
//
//                            }
//                        }
//                    } );
//
//
//
//    }
}