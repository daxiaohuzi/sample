package com.sample;

import com.library.Activity.Fragment.BaseBackStackFragment;

/**
 * Created by xiaoye on 2016/4/29.
 */
public class BezierFragment extends BaseBackStackFragment<MainActivity>{
    @Override
    protected int getContentView() {
        return R.layout.bezier_layout;
    }
}
