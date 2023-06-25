package com.ocr.navigation.framgentHome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.ocr.navigation.Adapter.ImageAdapter;
import com.ocr.navigation.OOP.Image;
import com.ocr.navigation.R;
import com.ocr.navigation.my_interface.IntegerCallBack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator3;


public class WomenFragment extends Fragment {
    private ViewPager2 mViewPager2;
    private CircleIndicator3 mIndicator3;
    private CircleImageView imgMacNgoai,
            imgSoMi,imgThun,imgQuanDai, imgQuanShort,
            imgDam, imgDoMacNha,imgDoLot,imgVay;
    private View view;
    private LinearLayout linearLayout;

    private List<Image> imageList;
    private IntegerCallBack integerCallBack;

    private TableLayout tableLayout;

    public WomenFragment(IntegerCallBack listener){
        this.integerCallBack = listener;
    }

    private Handler mHandler= new Handler( Looper.getMainLooper());
    private Runnable mRunnable= new Runnable() {
        @Override
        public void run() {
            int troi =  mViewPager2.getCurrentItem();
            if (troi ==imageList.size()-1){
                mViewPager2.setCurrentItem( 0 );
            }else {
                mViewPager2.setCurrentItem( troi+1 );
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate( R.layout.fragment_women, container, false );
        unitUI();
        onClick();
        loadURLImg();
        mViewPager2.setOffscreenPageLimit( 3 );
        mViewPager2.setClipToPadding( false );
        mViewPager2.setClipChildren( false );
        imageList=getListImage();
        ImageAdapter imageAdapter= new ImageAdapter(getActivity(), imageList );
        mViewPager2.setAdapter( imageAdapter );
        mIndicator3.setViewPager( mViewPager2 );
        mViewPager2.registerOnPageChangeCallback( new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected( position );
                mHandler.removeCallbacks( mRunnable );
                mHandler.postDelayed( mRunnable,3000 );
            }
        } );
        return view;
    }

    private void onClick() {
        tableLayout.setOnClickListener( v-> {
            integerCallBack.integerCallBack( 2 );
        } );
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
        mViewPager2= view.findViewById( R.id.view_page_top );
        mIndicator3= view.findViewById( R.id.circleIndicator3 );
        tableLayout = view.findViewById( R.id.tb_featured_category );
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
    private List<Image> getListImage() {
        List<Image> list =new ArrayList<>();
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/resf4a5e7b28f784c297c80659c6631cd5ffr.jpg") );
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/res57f14d48e4dc9d548297957579276507fr.jpg") );
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/rese18e42cfe86b45f40bd2da23e93cdfc1fr.jpg") );
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/res35d9299d01c3eec4246da9f7ab8c62a1fr.jpg") );
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/res069c5c13d5ed474c16a614406fb3de59fr.jpg") );
        return list;
    }

}