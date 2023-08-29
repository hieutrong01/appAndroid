package com.ocr.navigation.framgent;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ocr.navigation.ChangePasswordActivity;
import com.ocr.navigation.LichSuDonHangActivity;
import com.ocr.navigation.MainViewModel;
import com.ocr.navigation.OOP.User;
import com.ocr.navigation.OOP.UserManager;
import com.ocr.navigation.R;
import com.ocr.navigation.SignInActivity;
import com.ocr.navigation.UpDateMyProfile;

import java.util.List;

public class PesonFramgent extends Fragment {
    private View mView;
    private Button btnDangXuat,btnChinhSuaHoSo, btnChangePassword,  btnLichSu;


    private TextView tvUserName,tvEmail;

    private List<User> mListUser;

    private OnChangeAddressClickListener changeAddressClickListener;

    private MainViewModel mainViewModel;



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
        tvUserName=mView.findViewById( R.id.tv_show_user_name);
        tvEmail=mView.findViewById( R.id.tv_show_email );
        btnChinhSuaHoSo=mView.findViewById( R.id.btn_chinh_sua_profile );
        btnChangePassword=mView.findViewById(R.id.btn_change_password);
        btnLichSu=mView.findViewById( R.id.btn_lich_su_don_hang );
//        edtEmailSignIn = mView.findViewById(R.id.edt_email)


        tvUserName.setText(UserManager.getInstance().getCurrentUser().getUsername());
        tvEmail.setText(UserManager.getInstance().getCurrentUser().getEmail());

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

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), ChangePasswordActivity.class );
                startActivity(intent);
                return;
            }
        });



        btnChinhSuaHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpDateMyProfile.class);
                startActivity(intent);
            }
        });
        btnLichSu.setOnClickListener( v->{
            Intent intent= new Intent(getActivity(), LichSuDonHangActivity.class );
            startActivity( intent );
        } );


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
