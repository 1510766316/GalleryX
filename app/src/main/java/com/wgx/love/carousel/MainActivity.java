package com.wgx.love.carousel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.azoft.carousellayoutmanager.DefaultChildSelectionListener;
import com.wgx.love.carousel.adapter.CardAdapter;
import com.wgx.love.carousel.bean.PictureInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = "MainActivity";
    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<PictureInfo> mList = new ArrayList<>();
    private CarouselLayoutManager carouselLayoutManager;
    private CardAdapter mCardAdapter;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        initView();
        addData();
        addListener();
    }

    private void initView() {
        PictureInfo info;
        for (int i = 0; i < 20; i++) {
            info = new PictureInfo();
            if (i % 2 == 0){
                info.setImg(getDrawable(R.mipmap.rc1));
            }else {
                info.setImg(getDrawable(R.mipmap.rc2));
            }
            info.setName("galleryID:"+i);
            mList.add(info);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mCardAdapter= new CardAdapter(mList);

        carouselLayoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL,true);
        carouselLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        carouselLayoutManager.setMaxVisibleItems(2);

        mRecyclerView.setLayoutManager(carouselLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mCardAdapter);
        // enable center post scrolling auto
        mRecyclerView.addOnScrollListener(new CenterScrollListener());
        // enable center post touching on item and item click listener
        DefaultChildSelectionListener.initCenterItemListener(new DefaultChildSelectionListener.OnCenterItemClickListener() {
            @Override
            public void onCenterItemClicked(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManager carouselLayoutManager, @NonNull final View v) {
                final int position = recyclerView.getChildLayoutPosition(v);
                final String msg = String.format(Locale.US, "Item %1$d was clicked", position);
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        }, mRecyclerView, carouselLayoutManager);

        carouselLayoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (CarouselLayoutManager.INVALID_POSITION != adapterPosition) {
                    //Toast.makeText(mContext, "ItemSelectionListener", Toast.LENGTH_SHORT).show();
/*
                    adapter.mPosition[adapterPosition] = (value % 10) + (value / 10 + 1) * 10;
                    adapter.notifyItemChanged(adapterPosition);
*/
                }
            }
        });
    }

    private void addListener() {

    }

    private void addData() {

    }

    public void onClick(View v) {
        // mRecyclerView.scrollToPosition(6);
        carouselLayoutManager.scrollToPosition(4);
    }
}
