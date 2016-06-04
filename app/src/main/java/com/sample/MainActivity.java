package com.sample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.library.Activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        StatusBarUtils.from(this)
//                .setLightStatusBar(true)
//                .setTransparentStatusbar(true)
////                .setActionbarView(mNavigationBar)
//                .process();

//        if (isTranslucentStatusBar()) {
//            ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
//            View parentView = contentFrameLayout.getChildAt(0);
//            if (parentView != null && Build.VERSION.SDK_INT >= 14) {
//                parentView.setFitsSystemWindows(true);
//            }
//        }


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), MainActivity.this.getApplicationContext());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        // 若设置了该属性 则viewpager会缓存指定数量的Fragment
        // mViewPager.setOffscreenPageLimit(VIEWPAGER_OFF_SCREEN_PAGE_LIMIT);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getFragmentContentID() {
        return R.id.fragment_content;
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected int getOffsetStatusBarViewID() {
        return R.id.main_content;
    }

    @Override
    public void recurSelf() {
        Toast.makeText(MainActivity.this, "回到Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected boolean offsetStatusBar() {
        return true;
    }
}
