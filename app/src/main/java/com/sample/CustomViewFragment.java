package com.sample;

import android.view.View;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.library.view.widget.ImageButtonEx;

/**
 * Created by Administrator on 2016/6/3.
 */
public class CustomViewFragment extends BaseBackStackFragment<MainActivity> {
    private ImageButtonEx cameraFlashBtn;

    @Override
    protected int getContentView() {
        return R.layout.custom_view_layout;
    }

    @Override
    protected void init() {
        super.init();

        cameraFlashBtn = (ImageButtonEx) findView(R.id.cameraFlashBtn);
        cameraFlashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraFlashBtn.setChecked(!cameraFlashBtn.isChecked());
            }
        });
    }
}
