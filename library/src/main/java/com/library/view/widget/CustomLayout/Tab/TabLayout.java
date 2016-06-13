package com.library.view.widget.CustomLayout.Tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.library.R;
import com.library.Utils.Utils;
import com.library.view.widget.CustomLayout.Event.MultiTouchDelegate;

/**
 * Created by xiaoye on 2016/6/13.
 */
public class TabLayout extends ViewGroup implements View.OnClickListener {

    private ColorStateList mTextColor;
    private int mTextSize;
    private int mDrawablePadding;
    private View mSelectView;

    private int childCount;

    public TabLayout(Context context) {
        super(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttrs(context, attrs);
    }

    private void initAttrs(Context ctx, AttributeSet attrs) {
        TypedArray att = ctx.obtainStyledAttributes(attrs, R.styleable.TabLayout);
        mTextColor = att.getColorStateList(R.styleable.TabLayout_textColor);
        mTextSize = att.getDimensionPixelSize(R.styleable.TabLayout_textSize, 30);
        mDrawablePadding = att.getDimensionPixelSize(R.styleable.TabLayout_drawablePadding, 10);
        att.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), dp2px(56));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int size = getChildCount();

        int itemWidth = (r - l) / size;
        int viewH = b - t;

        if (childCount != size) {
            MultiTouchDelegate delegate = (MultiTouchDelegate) getTouchDelegate();
            if (delegate == null) {
                delegate = new MultiTouchDelegate(new Rect(), this);
                setTouchDelegate(delegate);
            }
            delegate.clearTouchDelegate();
            for (int i = 0; size > i; i++) {
                View child = getChildAt(i);
                int left = i * itemWidth;
                int right = left + itemWidth;
                delegate.addTouchDelegate(new Rect(left, 0, right, viewH), child);
            }
        }

        for (int i = 0; size > i; i++) {
            View child = getChildAt(i);
            int childW = child.getMeasuredWidth();
            int childH = child.getMeasuredHeight();
            int left = itemWidth * i + (itemWidth - childW) / 2;
            int top = (viewH - childH) / 2;
            int right = left + childW;
            int bottom = top + childH;
            child.layout(left, top, right, bottom);
        }

    }

    public int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale);
    }

    public void addTabItem(TabItem item) {
        addView(item);
    }

    public TabItem createTabItem(int bgID, String text) {
        TabItem item = new TabItem(getContext());
        item.setText(text);
        item.setCompoundDrawables(null, Utils.getCompoundDrawable(getContext(), bgID, 0.7f), null, null);
        item.setTextColor(mTextColor);
        item.setTextSize(mTextSize);
        item.setCompoundDrawablePadding(mDrawablePadding);
        item.setGravity(Gravity.CENTER);
        item.setOnClickListener(this);
        return item;
    }

    @Override
    public void onClick(View v) {
        if (mSelectView == v) return;
        if (mSelectView != null) mSelectView.setSelected(false);
        v.setSelected(true);
        mSelectView = v;
    }

    public void setSelect(int index) {
        View view = getChildAt(index);
        if (view != null) view.callOnClick();
    }
}
