package com.ocr.navigation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;

public class UpDateMyProfile extends AppCompatActivity {
    private static final int MY_REQUEST_CODE=10;
    private ImageView ivBack,imgAvatar;
    private EditText edtFullName,edtEmail;
    private Button btnUpdate;
    private Uri mUri;
    private ActivityResultLauncher<Intent> mActivityResultLauncher= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()==RESULT_OK){
                Intent intent=result.getData();
                if (intent==null){
                    return;
                }
                 Uri uri =intent.getData();
                setmUri( uri );
                try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap( getContentResolver(),mUri );
                    imgAvatar.setImageBitmap( bitmap );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    } );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_up_date_my_profile );
        initUI();
        setUserInformation();
        onClickListener();
    }


    public void initUI(){
        ivBack=findViewById( R.id.iv_back );
        imgAvatar=findViewById( R.id.img_avata );
        edtFullName=findViewById( R.id.edt_full_name );
        edtEmail=findViewById( R.id.edt_email1 );
        btnUpdate=findViewById( R.id.btn_up_date );

    }
    //set dữ liệu lên các item
    private void setUserInformation() {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            return;
        }
        edtFullName.setText( user.getDisplayName() );
        edtEmail.setText( user.getEmail() );
        //set ảnh lên giao diện, nếu ko load đc ảnh thì lấy ảnh mặc định.
        Glide.with(UpDateMyProfile.this).load( user.getPhotoUrl() ).
                error( R.drawable.ic_avatar ).into( imgAvatar );

    }


    private void onClickListener() {
        ivBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
        imgAvatar.setOnClickListener( new View.OnClickListener() {
            @Override
            //chọn ảnh từ Gallery, thì phải request permission READ_EXTERNAL_STORAGE
            public void onClick(View v) {
              openGallery();
            }
        } );
        btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUpdatProfile();
            }
        } );
    }
    private void onClickRequestPermission() {
        //từ android 6 trở lên thì ta thực hiện request permission

        if (checkSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE )== PackageManager.PERMISSION_GRANTED){

            openGallery();
        }else {

            String[] permission ={Manifest.permission.READ_EXTERNAL_STORAGE };

            requestPermissions( permission,MY_REQUEST_CODE );
            Toast.makeText( this, "hello", Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        if (requestCode==MY_REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent =new Intent();
        intent.setType( "image/*" );
        intent.setAction( Intent.ACTION_GET_CONTENT );
        mActivityResultLauncher.launch( Intent.createChooser( intent,"Select Picture" ) );
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }

    private void clickUpdatProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user== null){
            return;
        }
        String strFullname =edtFullName.getText().toString().trim();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName( strFullname )
                .setPhotoUri( mUri ).build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( UpDateMyProfile.this, "Update thanh cong", Toast.LENGTH_SHORT ).show();
                        }
                        else {
                            Toast.makeText( UpDateMyProfile.this, "Update fail", Toast.LENGTH_SHORT ).show();
                        }
                    }
                });
    }

}