package com.like.fitness.student;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.like.adapter.CoachDetailPagerAdapter;
import com.like.customview.PagerSlidingTabStrip;

public class CoachDetailActivity extends FragmentActivity implements SurfaceHolder.Callback {

    private PagerSlidingTabStrip mPagerTab;
    private ViewPager mDetailPager;
    private ViewGroup mTop;
    private SurfaceView mSurface;
    private SurfaceHolder mHolder;
    private MediaPlayer mPlayer;
    private ImageView mPlayIcon;

    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_detail);

        Intent intent = getIntent();
        if(intent != null)
            mId = intent.getIntExtra("id", -1);

        mPagerTab = (PagerSlidingTabStrip) findViewById(R.id.page_sliding_tab);
        mDetailPager = (ViewPager) findViewById(R.id.coach_detail_view_pager);
        mTop = (ViewGroup) findViewById(R.id.top);
        mDetailPager.setOffscreenPageLimit(2);//防止被销�?
        mPlayIcon = (ImageView) findViewById(R.id.play_icon);
        mSurface = (SurfaceView) findViewById(R.id.surface_view);
        initVideo();
        mSurface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPlayer.isPlaying()){
                    mPlayer.pause();
                    mPlayIcon.setVisibility(View.VISIBLE);
                } else {
                    mPlayer.start();
                    mPlayIcon.setVisibility(View.GONE);
                }
            }
        });

        initTab();

    }

    private void initTab() {
        mDetailPager.setAdapter(new CoachDetailPagerAdapter(getSupportFragmentManager(), mId));
        mPagerTab.setShouldExpand(true);
        mPagerTab.setFillViewport(true);
        mPagerTab.setIndicatorColorResource(R.color.red);
        mPagerTab.setTextColorStateResource(R.color.tab_text_color);
        mPagerTab.setDividerColor(0x00000000);
        mPagerTab.setTextSize(30);
        mPagerTab.setViewPager(mDetailPager);
    }

    private void initVideo() {
        mHolder = mSurface.getHolder();
        mHolder.addCallback(this);
        //为了可以播放视频或�?使用Camera预览，我们需要指定其Buffer类型
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mPlayer = new MediaPlayer();
        //然后指定�?��播放文件的路径，初始化MediaPlayer
        String dataPath = "http://resource.ising.migu.cn/GA/M01/02/FD/ChmFZVV8XKKAfELCAK4VVjBHGyk841.mp4";
        try {
            mPlayer.setDataSource(dataPath);
        } catch (Exception e) {
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPlayer.setDisplay(holder);
        mPlayer.prepareAsync();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void back(View v) {
        this.finish();
    }
}
