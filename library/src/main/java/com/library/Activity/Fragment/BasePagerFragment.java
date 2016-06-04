package com.library.Activity.Fragment;

import com.library.Activity.BaseActivity;

/**
 * Created by xiaoye on 2016/4/11.
 */
public abstract class BasePagerFragment<A extends BaseActivity> extends BaseLazyLoadFragment<A> {



    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
        if (getUserVisibleHint()) {
            isVisible = true;
            onShow();
        } else {
            isVisible = false;
            onHide();
        }
    }


}
