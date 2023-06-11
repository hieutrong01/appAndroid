package com.ocr.navigation.framgentHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.ocr.navigation.R;
import com.ocr.navigation.framgent.SearchFramgent;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class WomenFragment extends Fragment {
    private CircleImageView imgMacNgoai,
            imgSoMi,imgThun,imgQuanDai, imgQuanShort,
            imgDam, imgDoMacNha,imgDoLot,imgVay;
    private View view;
    private LinearLayout linearLayout, mLinearLayout;
    private SearchFramgent searchFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate( R.layout.fragment_women, container, false );
        unitUI();
        loadURLImg();
        onClickLisner();
        return view;
    }


    private void unitUI() {
        imgMacNgoai=view.findViewById( R.id.img_mac_ngoai );
        imgSoMi=view.findViewById( R.id.img_so_mi );
        imgThun=view.findViewById( R.id.img_ao_thun );
        imgQuanDai=view.findViewById( R.id.img_quan_dai );
        imgQuanShort=view.findViewById( R.id.img_quan_short );
        imgDam=view.findViewById( R.id.img_dam );
        imgDoMacNha=view.findViewById( R.id.img_do_mac_nha );
        imgDoLot=view.findViewById( R.id.img_do_lot );
        imgVay=view.findViewById( R.id.img_vay );
        linearLayout=view.findViewById( R.id.linear_layout );
    }
    private void loadURLImg() {
        String imageUrl1 = "https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/453032/item/goods_69_453032.jpg?width=750";
        String imageUrl2 = "https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/455734/item/goods_11_455734.jpg?width=750";
        String imageUrl3 = "https://im.uniqlo.com/global-cms/spa/res9200ec3e939f4a5f6291d457cff883c9fr.jpg";
        String imageUrl4 = "https://im.uniqlo.com/global-cms/spa/res8d2dba423357a481bf10328ad3b32b9dfr.jpg";
        String imageUrl5 = "https://im.uniqlo.com/global-cms/spa/res13fe7726a3d4dd0ed41d0b1c198165c3fr.jpg";
        String imageUrl6 = "https://im.uniqlo.com/global-cms/spa/res9ca2a6f0398cdfcee8cf865bcc30e3a0fr.jpg";
        String imageUrl7 = "https://im.uniqlo.com/global-cms/spa/res810be664a76c2c05ae88fa3456acddd6fr.jpg";
        String imageUrl8 = "https://im.uniqlo.com/global-cms/spa/res87c89d085fb11138adbd9cdecb6d368cfr.jpg";
        String imageUrl9 = "https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/453479/item/goods_66_453479.jpg?width=750";

        Picasso.get().load(imageUrl1).into(imgMacNgoai);
        Picasso.get().load(imageUrl2).into(imgSoMi);
        Picasso.get().load(imageUrl3).into(imgThun);
        Picasso.get().load(imageUrl4).into(imgQuanDai);
        Picasso.get().load(imageUrl5).into(imgQuanShort);
        Picasso.get().load(imageUrl6).into(imgDam);
        Picasso.get().load(imageUrl7).into(imgDoMacNha);
        Picasso.get().load(imageUrl8).into(imgDoLot);
        Picasso.get().load(imageUrl9).into(imgVay);
    }
    private void onClickLisner() {
        linearLayout.setOnClickListener( v->{

        } );

    }

}