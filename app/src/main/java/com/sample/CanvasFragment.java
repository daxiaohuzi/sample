package com.sample;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.sample.View.CanvasView;

/**
 * Created by xiaoye on 2016/4/22.
 */
public class CanvasFragment extends BaseBackStackFragment<MainActivity> {
    @Override
    protected int getContentView() {
        return R.layout.canvas_layout;
    }

    @Override
    protected void init() {
        super.init();
        ((CanvasView)getView().findViewById(R.id.canvas_view)).startAnim();
    }
}
