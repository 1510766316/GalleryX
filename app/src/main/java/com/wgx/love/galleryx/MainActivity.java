package com.wgx.love.galleryx;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wgx.love.galleryx.adapter.GalleryAdapter;
import com.wgx.love.galleryx.bean.PictureInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    private Context mContext;
    private RecyclerView mRecyclerView;
    private GalleryAdapter mGalleryAdapter;

    private int mCurrentItemOffset = 0;
    private int mCurrentItemPos;
    private int mOnePageWidth;
    private float mScale = 0.05f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        initView();
        addListener();
        addData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mGalleryAdapter = new GalleryAdapter(mContext);
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mGalleryAdapter);
        mOnePageWidth = dip2px(mContext,140);
    }


    private void addListener() {
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    private void addData(){
        mGalleryAdapter.getList().clear();
        mGalleryAdapter.addAllAndNotify(getData());
    }


    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // dx>0则表示右滑, dx<0表示左滑, dy<0表示上滑, dy>0表示下滑
            mCurrentItemOffset += dx;
            computeCurrentItemPos();
            onScrolledChangedCallback();
        }
    };
    /**
     * 计算当前位置
     */
    private void computeCurrentItemPos(){
        mCurrentItemPos = mCurrentItemOffset/mOnePageWidth + 2;
    }

    /**
     * RecyclerView位移事件监听, view大小随位移事件变化
     */
    private void onScrolledChangedCallback() {
        int offset = mCurrentItemOffset - mCurrentItemPos * mOnePageWidth;
        //滑动某页的百分比
        float percent = (float) Math.max(Math.abs(offset) * 1.0 / mOnePageWidth, 0.0001);

        View firstLeftView = null;
        View twoLeftView = null;
        View currentView;
        View firstRightView = null;
        View twoRightView = null;
        if (mCurrentItemPos > 0) {
            firstLeftView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos - 2);
        }
        if (mCurrentItemPos > 0) {
            twoLeftView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos - 1);
        }
        currentView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos);
        if (mCurrentItemPos < mRecyclerView.getAdapter().getItemCount() - 1) {
            firstRightView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos + 1);
        }
        if (mCurrentItemPos < mRecyclerView.getAdapter().getItemCount() - 2) {
            twoRightView = mRecyclerView.getLayoutManager().findViewByPosition(mCurrentItemPos + 2);
        }
        Log.i(TAG,"---- -------------\t\n");
        Log.i(TAG,"---->mOnePageWidth:"+mOnePageWidth);
        Log.i(TAG,"---->mCurrentItemOffset:"+mCurrentItemOffset);
        Log.i(TAG,"---->mCurrentItemPos:"+mCurrentItemPos);
        Log.i(TAG,"---->mOnePageWidth:"+mOnePageWidth);
        Log.i(TAG,"---->percent:"+percent);
        Log.i(TAG,"---- -------------\t\n");
        if (firstLeftView != null) {
            twoLeftView.setAlpha(0.3f);
        }
        if (twoLeftView != null) {
            // y = (1 - mScale)x + mScale
            //leftView.setAlpha((1 - mScale) * percent + mScale);
            twoLeftView.setAlpha(0.6f);
        }
        if (currentView != null) {
            // y = (mScale - 1)x + 1
            currentView.setAlpha(1);
            //currentView.setAlpha((mScale - 1) * percent + 1);
        }
        if (firstRightView != null) {
            // y = (1 - mScale)x + mScale
            //rightView.setAlpha((1 - mScale) * percent + mScale);
            firstRightView.setAlpha(0.6f);
        }
        if (twoRightView != null) {
            twoRightView.setAlpha(0.3f);
        }
    }

    private List<PictureInfo> getData(){
        List<PictureInfo> list =new ArrayList<>();
        PictureInfo info;
        for (int i=0;i<20;i++){
            info = new PictureInfo();
            info.setId(i+"");
            info.setName("图片:"+i);
            list.add(info);
        }
        return list;
    }


    /**
     * 分辨率从 dp 的单位 转成为 px(像素)
     * @param context
     * @param dpValue
     * @return
     */
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
