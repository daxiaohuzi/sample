package com.library.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.Activity.BaseActivity;

/**
 * Created by Administrator on 2016/3/28.
 */
public abstract class BaseFragment<A extends BaseActivity> extends Fragment {


    /**
     * 是否初始化
     */
    protected boolean isInit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(getContentView(), null, false);
        fragmentView.setClickable(true);
        return fragmentView;
    }

    protected boolean offsetStatusBar() {
        return false;
    }


    public A getSelftActivity() {
        return (A) getActivity();
    }

    public boolean handlerKey(int keyCode, KeyEvent event) {
        return false;
    }

    public <T extends View> T findView(@IdRes int id) {
        return (T) getView().findViewById(id);
    }


    @CallSuper
    /**
     * 初始化数据
     */
    protected void init() {

        if (getSelftActivity().isCanOffsetStatusBar() && offsetStatusBar()) {
            int contentID = getOffsetStatusBarViewID();
            if (contentID != -1) {
                View view = getView().findViewById(contentID);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                int barHeight = getSelftActivity().getStatusBarHeight();
                if (params.height > 0)
                    params.height += barHeight;
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + barHeight, view.getPaddingRight(), view.getPaddingBottom());
                view.requestLayout();
            }
        }
//        if (isBindViews()) ButterKnife.bind(this, getView());
    }

//    protected boolean isBindViews() {
//        return false;
//    }

    protected int getOffsetStatusBarViewID() {
        return -1;
    }

    protected abstract int getContentView();

    /**
     * Fragment显示出来
     */
    protected void onShow() {
    }

    /**
     * Fragment隐藏掉
     */
    protected void onHide() {
    }

    /**
     * 如果是ViewPager 则代码里把事件下发给子Fragment
     */
    public boolean receiveData(Object data) {
        return false;
    }

    public boolean receiveData(String tag, Object data) {
        return false;
    }

    /**
     * 是否接受数据
     */
    public boolean isReceiveData() {
        return false;
    }

    @Override
    public void onDestroy() {
//        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
