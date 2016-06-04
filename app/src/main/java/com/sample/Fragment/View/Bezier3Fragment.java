package com.sample.Fragment.View;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.sample.MainActivity;
import com.sample.R;

import de.greenrobot.event.EventBus;

/**
 * Created by xiaoye on 2016/4/29.
 */
public class Bezier3Fragment extends BaseBackStackFragment<MainActivity>{
    @Override
    protected int getContentView() {

        return R.layout.bezier3_layout;
    }
}
