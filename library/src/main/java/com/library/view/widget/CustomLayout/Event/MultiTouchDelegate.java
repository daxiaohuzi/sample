
package com.library.view.widget.CustomLayout.Event;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by xiaoye on 2016/4/3.
 */
public class MultiTouchDelegate extends TouchDelegate {

    ArrayList<Rect> mBounds;
    ArrayList<Rect> mSlopBounds;
    ArrayList<View> mTouchDelegateViews;
    int mSlop;
    boolean mDelegateTargeted;
    private Rect mUseTouchDelegateBounds;
    private Rect mUseSlopTouchDelegateBounds;
    private View mUseTouchView;


    public MultiTouchDelegate(Rect bounds, View delegateView) {
        super(bounds, delegateView);

        if (mBounds == null) {
            mBounds = new ArrayList<>(4);
            mSlopBounds = new ArrayList<>(4);
            mTouchDelegateViews = new ArrayList<>(4);
        }

        try {
            mBounds.add(bounds);

            Field mSlopField = TouchDelegate.class.getDeclaredField("mSlop");
            mSlopField.setAccessible(true);
            mSlop = (int) mSlopField.get(this);


            Field boundsField = TouchDelegate.class.getDeclaredField("mSlopBounds");
            boundsField.setAccessible(true);
            mSlopBounds.add((Rect) boundsField.get(this));

            mTouchDelegateViews.add(delegateView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearTouchDelegate() {
        mBounds.clear();
        mSlopBounds.clear();
        mTouchDelegateViews.clear();
    }

    public void addTouchDelegate(Rect bounds, View delegateView) {
        mBounds.add(bounds);
        Rect slopBound = new Rect(bounds);
        slopBound.inset(-mSlop, -mSlop);
        mSlopBounds.add(slopBound);
        mTouchDelegateViews.add(delegateView);
    }


//    public TouchDelegate(Rect bounds, View delegateView) {
//        mBounds = bounds;
//
//        mSlop = ViewConfiguration.get(delegateView.getContext()).getScaledTouchSlop();
//        mSlopBounds = new Rect(bounds);
//        mSlopBounds.inset(-mSlop, -mSlop);
//        mDelegateView = delegateView;
//    }

    /**
     * Will forward touch events to the delegate view if the event is within the bounds
     * specified in the constructor.
     *
     * @param event The touch event to forward
     * @return True if the event was forwarded to the delegate, false otherwise.
     */
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        boolean sendToDelegate = false;
        boolean hit = true;
        boolean handled = false;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                for (int i = 0, count = mBounds.size(); count > i; i++) {
                    Rect bounds = mBounds.get(i);
                    if (bounds.contains(x, y)) {
                        mUseTouchDelegateBounds = bounds;
                        mUseSlopTouchDelegateBounds = mSlopBounds.get(i);
                        mUseTouchView = mTouchDelegateViews.get(i);
                        mDelegateTargeted = true;
                        sendToDelegate = true;
                        break;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                sendToDelegate = mDelegateTargeted;
                if (sendToDelegate) {
                    Rect slopBounds = mUseSlopTouchDelegateBounds;
                    if (slopBounds != null && !slopBounds.contains(x, y)) {
                        hit = false;
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                sendToDelegate = mDelegateTargeted;
                mDelegateTargeted = false;
                break;
        }
        if (sendToDelegate) {
            final View delegateView = mUseTouchView;

            if (hit) {
                // Offset event coordinates to be inside the target view
                event.setLocation(delegateView.getWidth() / 2, delegateView.getHeight() / 2);
            } else {
                // Offset event coordinates to be outside the target view (in case it does
                // something like tracking pressed state)
                int slop = mSlop;
                event.setLocation(-(slop * 2), -(slop * 2));
            }
            handled = delegateView.dispatchTouchEvent(event);
        }
        return handled;
    }
}
