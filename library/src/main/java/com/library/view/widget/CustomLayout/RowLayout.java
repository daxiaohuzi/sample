package com.library.view.widget.CustomLayout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.library.R;
import com.library.view.widget.CustomLayout.Event.MultiTouchDelegate;

/**
 * Created by xiaoye on 2016/4/16.
 */
public class RowLayout extends ViewGroup {

    /**
     * View的横向间距均分
     */
    public static final int HORIZONTAL_PADDING_WEIGHT_MODE = 1;

    /**
     * 左边右边边界固定
     */
    public static final int HORIZONTAL_PADDING_FIXED_MODE = 2;

    /**
     * View的竖向间距均分
     */
    public static final int VERTICAL_PADDING_WEIGHT_MODE = 3;

    /**
     * 上下边界固定
     */
    public static final int VERTICAL_PADDING_FIXED_MODE = 4;

    private int _HorizontalPaddingMode = HORIZONTAL_PADDING_FIXED_MODE;
    private int _VerticalPaddingMode;

    private int mHorizontalPaddingValue = 100;
    private int mVerticalPaddingValue;

    private boolean isDelegateTouch = true;


    public RowLayout(Context context) {
        super(context);
    }

    public RowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        LinearLayout.VERTICAL
    }

    public RowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//    onMeasure传入的两个参数是由上一层控件传入的大小，有多种情况，重写该方法时需要对计算控件的实际大小，
//    然后调用setMeasuredDimension(int, int)设置实际大小。
//
//    onMeasure传入的widthMeasureSpec和heightMeasureSpec不是一般的尺寸数值，而是将模式和尺寸组合在一起的数值。
//    我们需要通过int mode = MeasureSpec.getMode(widthMeasureSpec)得到模式，用int size = MeasureSpec.getSize(widthMeasureSpec)
//    得到尺寸。
//
//    mode共有三种情况，取值分别为MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY, MeasureSpec.AT_MOST。
//
//    MeasureSpec.EXACTLY是精确尺寸，当我们将控件的layout_width或layout_height指定为具体数值时如andorid:layout_width="50dip"，
//    或者为FILL_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。
//
//    MeasureSpec.AT_MOST是最大尺寸，当控件的layout_width或layout_height指定为WRAP_CONTENT时，
//    控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可。
//    因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
//
//    MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多，一般都是父控件是AdapterView，通过measure方法传入的模式。

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int childCound = getChildCount();
        for (int i = 0; childCound > i; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }

//        printID(getId());
//        print(MeasureSpec.getMode(widthMeasureSpec));
//        print(MeasureSpec.getMode(heightMeasureSpec));
//        printID(getId());


    }

    private void printID(int id) {
        if (id == R.id.view1) {
            System.out.println("View1");
        } else if (id == R.id.view2) {
            System.out.println("View2");
        } else if (id == R.id.view3) {
            System.out.println("View3");
        }
    }

    private void print(int mode) {
        if (mode == MeasureSpec.EXACTLY) {
            System.out.println("MeasureSpec.EXACTLY");
        } else if (mode == MeasureSpec.AT_MOST) {
            System.out.println("MeasureSpec.AT_MOST");
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            System.out.println("MeasureSpec.UNSPECIFIED");
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        final int Width = r - l;
        final int Height = b - t;
        final int childCount = getChildCount();

        int childWidth = 0;
        for (int i = 0; childCount > i; i++) {
            View view = getChildAt(i);
            childWidth += view.getMeasuredWidth();
        }

        int childPadding = 0;

        if (_HorizontalPaddingMode == HORIZONTAL_PADDING_FIXED_MODE) {
            childPadding = (Width - childWidth - mHorizontalPaddingValue * 2) / (childCount - 1);
        } else if (_HorizontalPaddingMode == HORIZONTAL_PADDING_WEIGHT_MODE) {
            childPadding = (Width - childWidth) / (childCount + 1);
        }

        int lastX = 0;

        int viewLeft = 0;
        int viewTop = 0;
        int viewBottom = 0;
        int viewRight = 0;
        MultiTouchDelegate touchDelegate = null;
        int padding2 = 0;
        if (isDelegateTouch) {
            touchDelegate = (MultiTouchDelegate) getTouchDelegate();
            if (touchDelegate != null) touchDelegate.clearTouchDelegate();
            padding2 = childPadding / 2;
        }

        int lastTouchDelegateX = 0;

        for (int i = 0; childCount > i; i++) {

            View view = getChildAt(i);
            if (i == 0) {
                if (_HorizontalPaddingMode == HORIZONTAL_PADDING_FIXED_MODE) {
                    viewLeft = mHorizontalPaddingValue;
                } else if (_HorizontalPaddingMode == HORIZONTAL_PADDING_WEIGHT_MODE) {
                    viewLeft = childPadding;
                }
            } else {
                viewLeft = lastX + childPadding;
            }

            viewTop = (Height - view.getMeasuredHeight()) / 2;
            viewRight = viewLeft + view.getMeasuredWidth();
            viewBottom = viewTop + view.getMeasuredHeight();

            view.layout(viewLeft, viewTop, viewRight, viewBottom);
            lastX = viewRight;
            if (isDelegateTouch) {
                if (touchDelegate == null) {
                    lastTouchDelegateX = viewRight + padding2;
                    Rect rect = new Rect(0, 0, lastTouchDelegateX, getHeight());
                    touchDelegate = new MultiTouchDelegate(rect, view);
                    setTouchDelegate(touchDelegate);
                } else {
                    Rect rect;
                    if (i == 0) {
                        rect = new Rect(0, 0, viewRight + padding2, Height);
                        touchDelegate.addTouchDelegate(rect, view);
                    } else if ((childCount - 1) == i) {
                        rect = new Rect(lastTouchDelegateX, 0, Width, Height);
                        touchDelegate.addTouchDelegate(rect, view);
                    } else {
                        rect = new Rect(lastTouchDelegateX, 0, viewRight + padding2, Height);
                        touchDelegate.addTouchDelegate(rect, view);
                    }
                    lastTouchDelegateX = rect.right;
                }
            }

        }

    }
}
