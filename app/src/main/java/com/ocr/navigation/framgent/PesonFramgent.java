package com.ocr.navigation.framgent;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.ocr.navigation.OOP.UserManager;
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
    public void showUserInformation() {
        UserManager user = UserManager.getInstance();
        // Kiểm tra user nếu không tồn tại thì return
        if (user == null || user.getCurrentUser() == null) {
            Toast.makeText( getActivity(), "a", Toast.LENGTH_SHORT ).show();
            return;
        } else {
            String name = user.getCurrentUser().getFull_name();
            String email = user.getCurrentUser().getEmail();

            // Kiểm tra name nếu có thì hiển thị, không thì ẩn đi
            if (TextUtils.isEmpty(name)) {
                tvName.setVisibility(View.GONE);
            } else {
                tvName.setVisibility(View.VISIBLE);
                tvName.setText(name);
            }
            tvEmail.setText(email);
        }
    }


}
