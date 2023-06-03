package com.ocr.navigation.framgentHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ocr.navigation.Adapter.ItemAdapter;
import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.R;

import java.util.ArrayList;
import java.util.List;

public class MenFragment extends Fragment {
   private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate( R.layout.fragment_men, container, false );
        return view;
    }
}
