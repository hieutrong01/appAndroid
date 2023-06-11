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

public class MenFragment extends Fragment {
    private CircleImageView imgMacNgoai,
            imgSoMi,imgThun,imgQuanDai, imgQuanShort,
            imgPolo, imgDoMacNha,imgDoLot;
    private View view;
    private LinearLayout linearLayout;
    private SearchFramgent searchFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate( R.layout.fragment_men, container, false );
        unitUI();
        loadURLImg();
        return view;
    }
    private void unitUI() {
        imgMacNgoai=view.findViewById( R.id.img_mac_ngoai );
        imgSoMi=view.findViewById( R.id.img_so_mi );
        imgThun=view.findViewById( R.id.img_ao_thun );
        imgQuanDai=view.findViewById( R.id.img_quan_dai );
        imgQuanShort=view.findViewById( R.id.img_quan_short );
        imgPolo=view.findViewById( R.id.img_polo );
        imgDoMacNha=view.findViewById( R.id.img_do_mac_nha );
        imgDoLot=view.findViewById( R.id.img_do_lot );
        linearLayout=view.findViewById( R.id.linear_layout );
    }
    private void loadURLImg() {
        String imageUrl1 = "https://im.uniqlo.com/global-cms/spa/res4834fdd5cdf58a93a28010d6a3757696fr.jpg";
        String imageUrl2 = "https://im.uniqlo.com/global-cms/spa/res9fc25ae2875affafa76990a605cefb5dfr.jpg";
        String imageUrl3 = "https://im.uniqlo.com/global-cms/spa/resf3d9c854951fe4ec9add3b2f545cd410fr.jpg";
        String imageUrl4 = "https://im.uniqlo.com/global-cms/spa/res0f532481e1253864af9d6c30abe3f707fr.jpg";
        String imageUrl5 = "https://im.uniqlo.com/global-cms/spa/res6fb8419fb0b905a1659bf52fff173450fr.jpg";
        String imageUrl6 = "https://im.uniqlo.com/global-cms/spa/res387115447eca49c744f29e9d6d268987fr.jpg";
        String imageUrl7 = "https://im.uniqlo.com/global-cms/spa/resb8e1122fad5f4cf2b749214c1d7d81fbfr.jpg";
        String imageUrl8 = "https://im.uniqlo.com/global-cms/spa/res54eec6ab75c5c0fdfd418f11a4c46363fr.jpg";

        Picasso.get().load(imageUrl1).into(imgMacNgoai);
        Picasso.get().load(imageUrl2).into(imgSoMi);
        Picasso.get().load(imageUrl3).into(imgThun);
        Picasso.get().load(imageUrl4).into(imgQuanDai);
        Picasso.get().load(imageUrl5).into(imgQuanShort);
        Picasso.get().load(imageUrl6).into(imgPolo);
        Picasso.get().load(imageUrl7).into(imgDoMacNha);
        Picasso.get().load(imageUrl8).into(imgDoLot);


    }
}
