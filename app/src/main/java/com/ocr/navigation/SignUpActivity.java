package com.ocr.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
   private EditText editEmail,editPassword;
   private Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
        initUI();
        initListener();
    }

    private void initUI(){
        editEmail=findViewById( R.id.edt_email );
        editPassword=findViewById(R.id.edt_password );
        btnSignUp=findViewById( R.id.btn_sign_up );

    }
    private void initListener() {
        btnSignUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        } );
    }

    private void onClickSignUp() {
        String strEmail = editEmail.getText().toString().trim();
        String strPassword = editPassword.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (TextUtils.isEmpty( strEmail )){
            Toast.makeText( this, "Vui lòng nhập Email", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (TextUtils.isEmpty( strPassword )){
            Toast.makeText( this, "Vui lòng nhập Password", Toast.LENGTH_SHORT ).show();
            return;
        }

            mAuth.createUserWithEmailAndPassword( strEmail, strPassword )
                    .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                            Toast.makeText(SignUpActivity.this, "Bạn đã đăng ký thành côngcông ",
//                                    Toast.LENGTH_SHORT).show();
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent( SignUpActivity.this, SignInActivity.class );
                                startActivity( intent );
                                Toast.makeText( SignUpActivity.this, "Bạn đã đăng ký thành côngcông ",
                                        Toast.LENGTH_SHORT ).show();
                                finishAffinity();


                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText( SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT ).show();

                            }
                        }
                    } );


        }
    }

