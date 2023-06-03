package com.ocr.navigation.framgentSearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.R;

public class ListProductFragment extends Fragment {
    private View mView;
    private TextView tvItemName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate( R.layout.fragment_list_product2, container, false );
        tvItemName = mView.findViewById(R.id.tv_id_item_men);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Item item = (Item) bundle.getSerializable("object_item");
            if (item != null) {
                tvItemName.setText(item.getItem());
            }
        }
        return mView;
    }
}