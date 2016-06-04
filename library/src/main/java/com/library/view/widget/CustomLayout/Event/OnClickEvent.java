package com.library.view.widget.CustomLayout.Event;

import android.view.View;

/**
 * Created by xiaoye on 2016/4/16.
 */
public abstract class OnClickEvent implements View.OnClickListener {

    private static long lastTime;

    public abstract void singleClick(View v);

    @Override
    public void onClick(View v) {
        if (onDoubClick()) {
            return;
        }
        singleClick(v);
    }

    final boolean onDoubClick() {
        final long currTime = System.currentTimeMillis();
        long durationTime = currTime - lastTime;
        lastTime = currTime;
        return durationTime > 500;
    }
}
