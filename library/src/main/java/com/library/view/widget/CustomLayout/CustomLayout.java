package com.library.view.widget.CustomLayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/8.
 */
public class CustomLayout extends ViewGroup {

    //添加在现有View的左边
    public static final int _ChildLeft = 1;
    //添加在现有View的右边
    public static final int _ChildRight = 2;
    //添加在中间
    public static final int _ChildMiddle = 3;
    //添加在中间View的左边  如果中间没有View则报错
    public static final int _ChildMiddleLeft = 4;
    //添加在中间View的右边  如果中间没有View则报错
    public static final int _ChildMiddleRight = 5;

    public static final int _ChildStartMiddle = 6;

    public static final int _ChildEndMiddle = 7;

    /**
     * 行结束
     */
    public static final int _ChildColumnEnd = 8;

    /**
     * 布局方式 行置顶
     */
    public static final int _LayoutModeTop = 9;
    /**
     * 布局方式 行置底
     */
    public static final int _LayoutModeBottom = 10;
    /**
     * 布局方式 行居中
     */
    public static final int _LayoutModeCenter = 11;

    /**
     * 填充父类 间距=(屏幕宽度-行View总宽度)/(view数量+1)
     */
    public static final int _ChildPaddingModeWeight = 12;

    /**
     * View之间的边距为指定值
     */
    public static final int _ClicdPaddingModeValue = 13;

    /**
     * 行列数
     */
    private int mColumn;
    /**
     * _ClicdPaddingModeValue 模式需要的Padding值
     */
    private int mPaddingValue;
    /**
     * padding设置用在DelegateTouch里
     */
    private int isPaddingDelegateTouch;

    private Drawable mDrawableLeft;

//    private int mDrawablePadding;
//
//    private Drawable mDrawableRight;


    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void addView(View view, int layoutMode) {

    }

    public static class row {

        ArrayList<column> columns;

        public row() {

            columns = new ArrayList<>(3);

        }
    }


    public static class column {
        ArrayList<View> middleViews;
        ArrayList<View> middleLeftViews;
        ArrayList<View> middleRightViews;
        ArrayList<View> leftViews;
        ArrayList<View> rightViews;
        View startMiddleView;
        View endMiddleView;

        public column() {
            middleViews = new ArrayList<>(3);
            middleLeftViews = new ArrayList<>(3);
            middleRightViews = new ArrayList<>(3);
            leftViews = new ArrayList<>(6);
            rightViews = new ArrayList<>(3);
        }

        void addView(View view, int layoutMode) {

            switch (layoutMode) {
                case _ChildLeft:
                    leftViews.add(view);
                    break;
                case _ChildRight:
                    rightViews.add(view);
                    break;
                case _ChildMiddle:
                    middleViews.add(view);
                    break;
                case _ChildMiddleLeft:
                    middleLeftViews.add(view);
                    break;
                case _ChildMiddleRight:
                    middleRightViews.add(view);
                    break;
                case _ChildStartMiddle:
                    startMiddleView = view;
                    break;
                case _ChildEndMiddle:
                    endMiddleView = view;
                    break;
            }

        }
    }
}
