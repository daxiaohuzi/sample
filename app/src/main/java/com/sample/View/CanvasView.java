package com.sample.View;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaoye on 2016/4/22.
 */
public class CanvasView extends View {

    private Paint mPaint;
    private int mTranslateValue;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.save();

        canvas.translate(200, mTranslateValue);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 100, mPaint);

        canvas.translate(200, mTranslateValue + 200);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(0, 0, 100, mPaint);

//        canvas.restore();

    }

    public void startAnim() {

//        ValueAnimator animator = new ValueAnimator();
//        animator.setStartDelay(1000);
////        animator.setPropertyName("mTranslateValue");
//        animator.setValues(PropertyValuesHolder.ofInt("mTranslateValue", 200, 400, 800, 1000));
//        animator.setDuration(200);
//        animator.start();

        ObjectAnimator anim = ObjectAnimator.ofInt(this, "mTranslateValue", 0, 100);
        anim.setDuration(2000);
        anim.setStartDelay(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int translate = (int) animation.getAnimatedValue();
//                mTranslateValue = translate
                offsetTopAndBottom(translate);
//                setTranslationY(translate);
//                mTranslateValue = translate;
//                invalidate();
            }
        });
        anim.start();
    }

    public int getmTranslateValue() {
        return mTranslateValue;
    }

    public void setmTranslateValue(int mTranslateValue) {
        this.mTranslateValue = mTranslateValue;
    }
}
