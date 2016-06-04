package com.library.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.Activity.BaseActivity;

/**
 * Created by xiaoye on 2016/4/11.
 */
public abstract class BaseBackStackFragment<A extends BaseActivity> extends BaseFragment<A> {



    @Override
    public void onStart() {
        super.onStart();

        if (!isInit) {
            isInit = true;
            init();
        }

    }
}
