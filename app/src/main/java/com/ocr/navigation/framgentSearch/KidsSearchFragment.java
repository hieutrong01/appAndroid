package com.ocr.navigation.framgentSearch;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ocr.navigation.Adapter.ItemAdapter;
import com.ocr.navigation.OOP.Item;
import com.ocr.navigation.R;
import com.ocr.navigation.my_activity.KidsListProductActivity;
import com.ocr.navigation.my_activity.MenListItem;
import com.ocr.navigation.my_interface.ClickItemMenSearch;

import java.util.ArrayList;
import java.util.List;

public class KidsSearchFragment extends Fragment {

  private RecyclerView mRecyclerView;
  private ItemAdapter mItemAdapter;
  private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate( R.layout.fragment_kids_search, container, false );
        mRecyclerView = view.findViewById( R.id.rcv_item_kids );
        mItemAdapter = new ItemAdapter( getActivity() );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false );
        mRecyclerView.setLayoutManager( linearLayoutManager );
        mItemAdapter.setData( getListItem(), new ClickItemMenSearch() {
            @Override
            public void onItemClick(Item item, int pos) {
                onClickGoToKidsListItem(item,pos);
            }
        } );
        mRecyclerView.setAdapter( mItemAdapter );

        //phân ngang giưax các item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( mRecyclerView.getContext(),linearLayoutManager.getOrientation() );
        mRecyclerView.addItemDecoration( dividerItemDecoration );
        return view;
    }
    private void onClickGoToKidsListItem(Item item,int pos) {
        Intent intent= new Intent(getActivity(), KidsListProductActivity.class );
        Bundle bundle= new Bundle();
        bundle.putInt( "pos",pos );
        bundle.putSerializable( "object_item",item );
        intent.putExtras( bundle );
        startActivity( intent );
    }
    private List<Item> getListItem(){
        List<Item> list = new ArrayList<>();
        list.add( new Item("ÁO"));
        list.add( new Item("ĐỒ MẶC NGOÀI"));
        list.add( new Item("QUẦN"));
        list.add( new Item("ĐỒ MẶC TRONG & ĐỒ LÓT"));
        list.add( new Item("ĐỒ MẶC NHÀ"));
        return list;
    }
}