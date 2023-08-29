package com.ocr.navigation.framgentHome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ocr.navigation.Adapter.ImageAdapter;
import com.ocr.navigation.Adapter.SanPhamNoiBatAdapter;
import com.ocr.navigation.ChiTietProductActivity;
import com.ocr.navigation.OOP.DataProduct;
import com.ocr.navigation.OOP.Image;
import com.ocr.navigation.OOP.Product;
import com.ocr.navigation.R;
import com.ocr.navigation.my_interface.APIService;
import com.ocr.navigation.my_interface.ClickItemProduc;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopFragment extends Fragment {
    private ViewPager2 mViewPager2;
    private CircleIndicator3 mIndicator3;
    private List<Image> imageList;
    private View view;
    private RecyclerView mRecyclerView, saleRecyclerView;
    private List<Product> mList;
    private SanPhamNoiBatAdapter adapter;
    private SanPhamNoiBatAdapter adapterSale;
    private VideoView videoView;

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
         view = inflater.inflate( R.layout.fragment_top, container, false );

        //setting img view_page
        mViewPager2= view.findViewById( R.id.view_page_top );
        mIndicator3= view.findViewById( R.id.circleIndicator3 );
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
        initUI();

        GridLayoutManager gridLayoutManager= new GridLayoutManager( getActivity(),3);
        mRecyclerView.setLayoutManager( gridLayoutManager );
        mRecyclerView.setNestedScrollingEnabled( false );
        adapter.setData( getListProduct( ), new ClickItemProduc() {
            @Override
            public void onItemProductClick(Product product) {
                onClickgotoChitiet(product);
            }

            @Override
            public void onClickFavoriteItem(int pos) {

            }
        } );
        mRecyclerView.setAdapter( adapter );
        //
        GridLayoutManager gridLayoutManager1= new GridLayoutManager( getActivity(),3);
        saleRecyclerView.setLayoutManager( gridLayoutManager1 );
        saleRecyclerView.setNestedScrollingEnabled( false );
        adapterSale.setData( getListProductSale( ), new ClickItemProduc() {
            @Override
            public void onItemProductClick(Product product) {
                onClickgotoChitiet(product);
            }

            @Override
            public void onClickFavoriteItem(int pos) {

            }
        } );
        saleRecyclerView.setAdapter( adapterSale );

//        MediaController mediaController= new MediaController( requireContext() );
//        mediaController.setAnchorView( videoView );

       // videoView.setMediaController( mediaController );


//       videoView.setVideoURI( Uri.parse( "https://image.uniqlo.com/UQ/ST3/jp/imagesother/sport-utility-wear/23ss_summer/img/common/hero_1-pc.mp4#t=0.001") );
//        // Lắng nghe sự kiện hoàn thành phát video
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                // Khi video phát xong, chạy video từ đầu (loop)
//                mediaPlayer.seekTo(0);
//                mediaPlayer.start();
//            }
//        });
//        videoView.start();

        return view;
    }
    public void initUI(){
        mRecyclerView =view.findViewById( R.id.recyc_product );
        saleRecyclerView=view.findViewById( R.id.recyc_product_sale );
    //   videoView=view.findViewById( R.id.video_view );
        adapter = new SanPhamNoiBatAdapter( getActivity() );
        adapterSale=new SanPhamNoiBatAdapter( getActivity() );

    }


    private List<Image> getListImage() {
        List<Image> list =new ArrayList<>();
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/res6b8ce45b8387ddcf7cec6a3e92848c23fr.jpg") );
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/resd4df83327e0165e6f1b21207cdbbcdeafr.jpg") );
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/res0883ec0339f52ea55c053dff593ecbbbfr.jpg") );
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/res2388cafae032a2125e66e05733ce5614fr.jpg") );
        list.add( new Image("https://im.uniqlo.com/global-cms/spa/reseef4ead8734e46647f914d68b13cc4dffr.jpg") );
        return list;
    }

    private List<Product> getListProduct() {
        List<Product> list = new ArrayList<>();

        callApiAoWomen();
        return list;
    }
    private List<Product> getListProductSale() {
        List<Product> list = new ArrayList<>();

        callApiProductSale();
        return list;
    }

    private void onClickgotoChitiet(Product product) {
        Intent intent = new Intent( getActivity(), ChiTietProductActivity.class );
        Bundle bundle= new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void callApiAoWomen() {
        APIService.apiServiceKids.getSanPhamNoiBat().enqueue( new Callback<DataProduct>() {
            @Override
            public void onResponse(Call<DataProduct> call, Response<DataProduct> response) {
                if (response.body() == null) return;
                DataProduct data = response.body();
                mList = data.getData();
                adapter.setData( mList, new ClickItemProduc() {
                    @Override
                    public void onItemProductClick(Product product) {
                        onClickgotoChitiet(product);
                    }

                    @Override
                    public void onClickFavoriteItem(int pos) {

                    }
                } );
                mRecyclerView.setAdapter( adapter );
            }
                    @Override
            public void onFailure(Call<DataProduct> call, Throwable t) {
                t.printStackTrace();
                //Toast.makeText( getActivity(), "Call api fall", Toast.LENGTH_SHORT ).show();

            }
        } );
    }

    private void callApiProductSale() {
        APIService.apiServiceKids.getSanPhamSale().enqueue( new Callback<DataProduct>() {
            @Override
            public void onResponse(Call<DataProduct> call, Response<DataProduct> response) {
                if (response.body() == null) return;
                DataProduct data = response.body();
                mList = data.getData();
                adapterSale.setData( mList, new ClickItemProduc() {
                    @Override
                    public void onItemProductClick(Product product) {
                        onClickgotoChitiet(product);
                    }

                    @Override
                    public void onClickFavoriteItem(int pos) {

                    }
                } );
                saleRecyclerView.setAdapter( adapterSale );
            }
            @Override
            public void onFailure(Call<DataProduct> call, Throwable t) {
                t.printStackTrace();

            }
        } );
    }


    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks( mRunnable );
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed( mRunnable,3000 );
    }
}