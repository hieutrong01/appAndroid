package com.ocr.navigation.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ocr.navigation.OOP.Thanhtoan;
import com.ocr.navigation.R;


public class MyDialog extends Fragment {

    private Thanhtoan thanhtoan;

    public MyDialog(Thanhtoan thanhtoan) {
        this.thanhtoan = thanhtoan;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_my_dialog, container, false );
    }
}