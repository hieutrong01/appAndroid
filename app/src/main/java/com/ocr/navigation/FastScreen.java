package com.ocr.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.ocr.navigation.OOP.UserManager;


public class FastScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fast_screen );


        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        }, 500 );
    }

    private void nextActivity() {
        UserManager userManager = UserManager.getInstance();

      //  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (userManager == null) {
            //chua login
            Intent intent = new Intent( this, SignInActivity.class );
            startActivity( intent );

        } else {
            //da login
            Intent intent = new Intent( this, MainActivity.class );
            startActivity( intent );

        }
      finish();
    }
}