package com.library.view.widget.CustomLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;


public class TextEditText extends EditText {

    private String mTitle;
    private int mTitlePadding;
    private Rect mRect;
    private float mTitleDrawY;

    public TextEditText(Context context) {
        super(context);
    }

    public TextEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (mRect == null) {
            mRect = new Rect();
            getPaint().getTextBounds("你好啊", 0, 3, mRect);
            setPadding(mRect.width() + getPaddingLeft() + 30, getPaddingTop(), getPaddingRight(), getPaddingBottom());
            mTitleDrawY = (getMeasuredHeight() - mRect.height()) / 2.f + mRect.height() - mRect.bottom /*+mRect.height()*/;
            invalidate();
            return;
        }

        canvas.drawText("你好啊", 0, mTitleDrawY, getPaint());
    }
}
