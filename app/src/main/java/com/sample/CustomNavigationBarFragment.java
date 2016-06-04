package com.sample;

import com.library.Activity.Fragment.BaseBackStackFragment;

/**
 * Created by xiaoye on 2016/4/23.
 */
public class CustomNavigationBarFragment extends BaseBackStackFragment<MainActivity> {
    @Override
    protected int getContentView() {
        return R.layout.custom_navigationbar_layout;
    }

    @Override
    protected void init() {
        super.init();


//        View view = getView().findViewById(com.library.R.id.main_content);
//        ViewGroup.LayoutParams params = view.getLayoutParams();
////        params.topMargin = StatusBarUtils.getStatusBarOffsetPx(this);
//        int statusBarHeight = StatusBarUtils.getStatusBarOffsetPx(getActivity());
//        if (params.height > 0)
//            params.height += statusBarHeight;
//        view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + statusBarHeight, view.getPaddingRight(), view.getPaddingBottom());
//        view.requestLayout();

    }

    @Override
    protected boolean offsetStatusBar() {
        return true;
    }

    @Override
    protected int getOffsetStatusBarViewID() {
        return R.id.navigation_bar;
    }
}
