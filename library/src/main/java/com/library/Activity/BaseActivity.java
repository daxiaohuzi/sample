package com.library.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.library.Activity.Fragment.FragmentStack;
import com.library.Code.Stack;
import com.library.Utils.StatusBarUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2016/3/28.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 防止重复添加
     */
    public static final int ADD_FRAGMENT_INTERVAL = 600;

    /**
     * 发送数据 只有一个目标
     */
    public static final int ONE = 1;
    /**
     * 发送数据 全部目标
     */
    public static final int ALL = 2;

    static {
        _ActivityStack = new Stack<>();
    }

    FragmentStack<BaseBackStackFragment> _FragmentStack;

    static final Stack<BaseActivity> _ActivityStack;


    private static int _StatusBarHeight;
    private boolean isCanOffsetStatusBar;
    private long mLastAddTime;


    @IntDef({ONE, ALL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PostDataMode {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (isTranslucentStatusBar()) {
////            getWindow().getDecorView().setFitsSystemWindows(true);
//            ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
//            View parentView = contentFrameLayout.getChildAt(0);
//            if (parentView != null && Build.VERSION.SDK_INT >= 14) {
//                parentView.setFitsSystemWindows(true);
//            }
//        }


        isCanOffsetStatusBar = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        if (_StatusBarHeight == 0) {
            _StatusBarHeight = StatusBarUtils.getStatusBarOffsetPx(this);
        }

        setContentView(getContentViewID());
//        getContentView();

        if (isCanOffsetStatusBar && offsetStatusBar()) {
            int contentID = getOffsetStatusBarViewID();
            if (contentID != -1) {
                View view = findViewById(contentID);
                ViewGroup.LayoutParams params = view.getLayoutParams();
//        params.topMargin = StatusBarUtils.getStatusBarOffsetPx(this);
                if (params.height > 0)
                    params.height += _StatusBarHeight;
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + _StatusBarHeight, view.getPaddingRight(), view.getPaddingBottom());
                view.requestLayout();
            }
        }

        _FragmentStack = new FragmentStack<>(this, getFragmentContentID());

        _ActivityStack.push(this);

    }

//    protected View getContentView() {
//        return null;
//    }

    /**
     * 跳转Fragment需要
     */
    protected abstract int getFragmentContentID();

    /**
     * 偏移View的ID
     */
    protected int getOffsetStatusBarViewID() {
        return -1;
    }

    /**
     * 4.4的才可以操作StatusBar
     */
    public boolean isCanOffsetStatusBar() {
        return isCanOffsetStatusBar;
    }

    public int getStatusBarHeight() {
        return _StatusBarHeight;
    }

    protected abstract int getContentViewID();

    protected boolean offsetStatusBar() {
        return false;
    }

    public void finishFragment() {
        finishFragment(/*-1,*/ 1);
    }

    /**
     * 关闭说有的Fragment
     */
    public void finishAllFragment() {
        finishFragment(-1);
    }

    public void finishFragment(/*int type,*/ int count) {

        try {
            _FragmentStack.finishFragment(count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (_FragmentStack.handlerKey(keyCode, event) || handlerKeyDown(keyCode, event))
            return true;

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        if (!_FragmentStack.isEmpty()) {
            finishFragment(1);
            return;
        }

        super.onBackPressed();
    }

    /**
     * 子类处理按钮操作在这里操作
     */
    protected boolean handlerKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public void addFragment(BaseBackStackFragment fragment, Bundle bundle) {

        long time = System.currentTimeMillis();
        if (time < mLastAddTime + ADD_FRAGMENT_INTERVAL) {
            return;
        }

        mLastAddTime = time;

        if (bundle != null) fragment.setArguments(bundle);
        _FragmentStack.addFragment(fragment);
    }

    public void addFragment(BaseBackStackFragment fragment) {
        addFragment(fragment, null);
    }


    /**
     * 自己显示出来了
     */
    public void recurSelf() {

    }

    @Override
    protected void onDestroy() {
        _FragmentStack.clear();
        _ActivityStack.pop();
        super.onDestroy();
    }

    /**
     * 是否接受数据
     */
    protected boolean isReceiveData() {
        return false;
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
     * 发送数据
     */
    public void sentData(String tag, Object data) {

        final int size = _ActivityStack.size();

        for (int i = 0; size > 0; i++) {
            BaseActivity activity = _ActivityStack.get(i);
            final Stack<BaseBackStackFragment> stack = activity._FragmentStack.getStack();
            final int fragmentSize = stack.size();

            for (int j = 0; fragmentSize > j; j++) {
                BaseBackStackFragment fragment = stack.get(j);
                if (fragment.isReceiveData() && fragment.receiveData(tag, data))
                    return;
            }
            if (activity.isReceiveData() && activity.receiveData(tag, data))
                return;

        }
    }

    /**
     * 发送数据
     */
    public void sentData(Object data, @PostDataMode int mode) {

        final int size = _ActivityStack.size();
        switch (mode) {
            case ONE:

                for (int i = size - 1; i >= 0; i--) {
                    BaseActivity activity = _ActivityStack.get(i);

                    final Stack<BaseBackStackFragment> stack = activity._FragmentStack.getStack();
                    final int fragmentSize = stack.size();
                    for (int j = fragmentSize - 1; j >= 0; j--) {
                        BaseBackStackFragment fragment = stack.get(j);
                        if (fragment.isReceiveData() && fragment.receiveData(data))
                            return;
                    }
                    if (activity.isReceiveData() && activity.receiveData(data))
                        return;
                }

                break;
            case ALL:

                for (int i = size - 1; i >= 0; i--) {
                    BaseActivity activity = _ActivityStack.get(i);
                    final Stack<BaseBackStackFragment> stack = activity._FragmentStack.getStack();
                    final int fragmentSize = stack.size();
                    for (int j = fragmentSize - 1; j >= 0; j--) {
                        BaseBackStackFragment fragment = stack.get(j);
                        if (fragment.isReceiveData()) {
                            fragment.receiveData(data);
                        }
                    }
                    if (activity.isReceiveData()) {
                        activity.receiveData(data);
                    }
                }
                break;
        }
    }
}
