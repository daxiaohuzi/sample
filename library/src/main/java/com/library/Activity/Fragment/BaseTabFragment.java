package com.library.Activity.Fragment;

import com.library.Activity.BaseActivity;

/**
 * Created by xiaoye on 2016/4/11.
 */
public abstract class BaseTabFragment<A extends BaseActivity> extends BaseLazyLoadFragment<A> {




    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        lazyLoad();
        if (!hidden) {
            isVisible = true;
            onShow();
        } else {
            isVisible = false;
            onHide();
        }
    }



}
