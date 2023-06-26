package com.ocr.navigation.framgent;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ocr.navigation.ChangeAddressActivity;
import com.ocr.navigation.OOP.UserManager;
import com.ocr.navigation.R;
import com.ocr.navigation.SignInActivity;
import com.ocr.navigation.MainViewModel;
import com.ocr.navigation.retrofit.com.ocr.navigation.User;

import java.util.ArrayList;
import java.util.List;

public class PesonFramgent extends Fragment {
    private View mView;
    private Button btnDangXuat,btnChinhSuaHoSo, btnChangePassword, btnChangeDiaChi;
    private EditText edtEmailSignIn, edtPasswordSignIn;
    private ImageView ivAvatar;
    private TextView tvName,tvEmail;

    private List<User> mListUser;

    private OnChangeAddressClickListener changeAddressClickListener;

    private MainViewModel mainViewModel;

    private ArrayList<String> mArrayLichSu;

    @Override
    public void onResume() {
        super.onResume();
        Log.e( "Check","reload framgent Person" );
    }

    public interface OnChangeAddressClickListener {
        void onChangeAddressClick();
    }

    public void setOnChangeAddressClickListener(OnChangeAddressClickListener listener) {
        changeAddressClickListener = listener;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView= inflater.inflate( R.layout.framgent_person,container,false );
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        initUI();
        onClickListener();
//        showUserInformation();

        mListUser = mainViewModel.users;
        return mView;
    }

    public void initUI(){
        btnDangXuat=mView.findViewById( R.id.btn_dang_xuat );
        ivAvatar=mView.findViewById( R.id.img_avatar );
        tvName=mView.findViewById( R.id.tv_name );
        tvEmail=mView.findViewById( R.id.tv_email );
        btnChinhSuaHoSo=mView.findViewById( R.id.btn_chinh_sua_profile );
        btnChangePassword=mView.findViewById(R.id.btn_change_password);
        btnChangeDiaChi=mView.findViewById(R.id.btn_change_address);
//        edtEmailSignIn = mView.findViewById(R.id.edt_email)
    }
    private void onClickListener() {
        btnDangXuat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), SignInActivity.class );
                startActivity(intent);
                UserManager.getInstance().dangXuat();
                getActivity().finish();
            }
        } );

//        btnChangePassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getActivity(), ChangePasswordActivity.class );
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });



        btnChangeDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                        String address = UserManager.getInstance().getCurrentUser().getAddress();
//                        String city = UserManager.getInstance().getCurrentUser().getCity();

                        // Gửi dữ liệu thông qua Intent
                        Intent intent = new Intent(getActivity(), ChangeAddressActivity.class);
//                        intent.putExtra("address", address);
//                        intent.putExtra("city", city);
                        startActivity(intent);

                        return;


                }
        });

    }


//    public void showUserInformation(){
//        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//        //check user neu ko co thi return
//        if (user == null){
//            return;
//        }else {
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//            //check name neu cos thi cho hien , ko thi an di
//            if (name== null){
//                tvName.setVisibility( View.GONE );
//            }else {
//                tvName.setVisibility( View.VISIBLE );
//                tvName.setText( name );
//            }
//            tvEmail.setText( email );
//            Glide.with( this ).load( photoUrl ).error( R.drawable.ic_avatar ).into( ivAvatar );
//        }
//    }


}
