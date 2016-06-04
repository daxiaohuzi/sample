package com.sample;

import android.widget.ImageView;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.library.view.widget.CustomLayout.myViewPager;

/**
 * Created by xiaoye on 2016/4/16.
 */
public class ViewPagerFragment extends BaseBackStackFragment<MainActivity>{

    private myViewPager viewPager;

    @Override
    protected void init() {
        super.init();
        viewPager = (myViewPager) getView();

        ImageView view = new ImageView(getActivity());
        view.setBackgroundResource(R.mipmap.image1);
        viewPager.addView(view);

        view = new ImageView(getActivity());
        view.setBackgroundResource(R.mipmap.image2);
        viewPager.addView(view);

        view = new ImageView(getActivity());
        view.setBackgroundResource(R.mipmap.image3);
        viewPager.addView(view);
    }

    @Override
    protected int getContentView() {
        return R.layout.viewpager_layout;
    }
}
