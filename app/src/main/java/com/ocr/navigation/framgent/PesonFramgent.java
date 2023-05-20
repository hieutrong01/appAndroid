package com.ocr.navigation.framgent;


import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ocr.navigation.R;
import com.ocr.navigation.SignInActivity;
import com.ocr.navigation.UpDateMyProfile;

import java.util.ArrayList;

public class PesonFramgent extends Fragment {
    private View mView;
    private Button btnDangXuat,btnChinhSuaHoSo;
    private ImageView ivAvatar;
    private TextView tvName,tvEmail;

   private ArrayList<String> mArrayLichSu;

    @Override
    public void onResume() {
        super.onResume();
        Log.e( "Check","reload framgent Person" );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView= inflater.inflate( R.layout.framgent_person,container,false );
        initUI();
        onClickListener();
        showUserInformation();

        return mView;
    }
    public void initUI(){
        btnDangXuat=mView.findViewById( R.id.btn_dang_xuat );
        ivAvatar=mView.findViewById( R.id.img_avatar );
        tvName=mView.findViewById( R.id.tv_name );
        tvEmail=mView.findViewById( R.id.tv_email );
        btnChinhSuaHoSo=mView.findViewById( R.id.btn_chinh_sua_profile );
    }
    private void onClickListener() {
        btnDangXuat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(getActivity(), SignInActivity.class );
                startActivity(intent);
                getActivity().finish();
            }
        } );
        btnChinhSuaHoSo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), UpDateMyProfile.class);
                startActivity( intent );
            }
        } );
    }
    public void showUserInformation(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        //check user neu ko co thi return
        if (user == null){
            return;
        }else {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            //check name neu cos thi cho hien , ko thi an di
            if (name== null){
                tvName.setVisibility( View.GONE );
            }else {
                tvName.setVisibility( View.VISIBLE );
                tvName.setText( name );
            }
            tvEmail.setText( email );
            Glide.with( this ).load( photoUrl ).error( R.drawable.ic_avatar ).into( ivAvatar );
        }
    }


}
