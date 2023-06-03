package com.ocr.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private LinearLayout layoutSignUp;
    private EditText edtEmailSignIn, edtPasswordSignIn;
    private Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_in );
        iniUi();
        initListener();
    }
    private void iniUi(){
        layoutSignUp =findViewById( R.id.layout_sign_up );
        edtEmailSignIn=findViewById( R.id.edt_email_in );
        edtPasswordSignIn=findViewById( R.id.edt_password_in );
        btnSignIn=findViewById( R.id.btn_sign_in );
    }
    private void  initListener(){
        layoutSignUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity( intent );
            }
        } );
        btnSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listClickSignUp();
            }
        } );
    }

    private void listClickSignUp() {
        String strEmail = edtEmailSignIn.getText().toString().trim();
        String strPassword = edtPasswordSignIn.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
         if (TextUtils.isEmpty( strEmail )){
            Toast.makeText( this, "Vui lòng nhập Email", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (TextUtils.isEmpty( strPassword )){
            Toast.makeText( this, "Vui lòng nhập Password", Toast.LENGTH_SHORT ).show();
            return;
        }
            mAuth.signInWithEmailAndPassword( strEmail, strPassword )
                    .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                            Toast.makeText(SignUpActivity.this, "Bạn đã đăng ký thành côngcông ",
//                                    Toast.LENGTH_SHORT).show();
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent( SignInActivity.this, MainActivity.class );
                                startActivity( intent );

                                finishAffinity();


                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText( SignInActivity.this, "Bạn nhập sai tài khoản hoặc mật khẩu.",
                                        Toast.LENGTH_SHORT ).show();

                            }
                        }
                    } );



    }
}