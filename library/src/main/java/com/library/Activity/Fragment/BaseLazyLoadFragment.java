package com.library.Activity.Fragment;

import com.library.Activity.BaseActivity;

/**
 * Created by xiaoye on 2016/4/11.
 */
public abstract class BaseLazyLoadFragment<A extends BaseActivity> extends BaseFragment<A> {
    /**
     * 是否可见状态
     */
    protected boolean isVisible;


    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     */
    protected void lazyLoad() {
        if (isInit || !isVisible) {
            return;
        }
        isInit = true;
        init();
    }

    @Override
    public void onStart() {
        super.onStart();
        lazyLoad();
    }
}
