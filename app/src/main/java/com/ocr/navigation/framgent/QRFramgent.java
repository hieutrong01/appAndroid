package com.ocr.navigation.framgent;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ocr.navigation.GioHangActivity;
import com.ocr.navigation.R;


public class QRFramgent extends Fragment {
    private View mView;
    private ImageView imgGioHang;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate( R.layout.framgent_qrcode,container,false );
        initUI();
        onClickList();
        return mView;
    }

    private void initUI() {
        imgGioHang=mView.findViewById( R.id.img_gio_hang );
    }
    private void onClickList() {
        imgGioHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), GioHangActivity.class);
                startActivity( intent );
            }
        } );
    }
    public void reloadData(){
        Toast.makeText( getActivity(),"reload QR",Toast.LENGTH_SHORT ).show();
    }





}
