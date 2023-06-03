package com.ocr.navigation.framgentSearch;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ocr.navigation.Adapter.ItemAdapter;
import com.ocr.navigation.MainActivity;
import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.R;
import com.ocr.navigation.my_activity.MenListItem;
import com.ocr.navigation.my_interface.ClickItemMenSearch;

import java.util.ArrayList;
import java.util.List;

public class MenSearchFragment extends Fragment {

    private RecyclerView rcvItemMen;
    private ItemAdapter itemAdapter;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_men_search, container, false);
        rcvItemMen = mView.findViewById(R.id.rcv_itemMen);
        mainActivity = (MainActivity) getActivity();
        itemAdapter = new ItemAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvItemMen.setLayoutManager(linearLayoutManager);
        rcvItemMen.addItemDecoration(new DividerItemDecoration(rcvItemMen.getContext(), linearLayoutManager.getOrientation()));

        itemAdapter.setData(getListItem(), new ClickItemMenSearch() {
            @Override
            public void onItemClick(Item item, int pos) {
                onClickGoToMenListItem(item, pos);
            }
        });
        rcvItemMen.setAdapter(itemAdapter);
        return mView;
    }

    // Đặt ListProductFragment vào trong FragmentContainerView của MenSearchFragment
    private void onClickGoToMenListItem(Item item, int pos) {
        Intent intent = new Intent(getActivity(), MenListItem.class );
        Bundle bundle = new Bundle();
        bundle.putInt( "pos", pos );
        bundle.putSerializable( "object_item",item );
        intent.putExtras( bundle );
        startActivity( intent );
    }


//    private void onClickGoToMenListItem(Item item) {
//        ListProductFragment menListItem = new ListProductFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("object_item", item);
//        menListItem.setArguments(bundle);
//        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.rtl_fragment_men_list, menListItem);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

    private List<Item> getListItem() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("ÁO"));
        list.add(new Item("ĐỒ MẶC NGOÀI"));
        list.add(new Item("QUẦN"));
        list.add(new Item("ĐỒ MẶC TRONG & ĐỒ LÓT"));
        list.add(new Item("ĐỒ MẶC NHÀ"));
        return list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}