package com.sample.Fragment.View;

import android.widget.TextView;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.sample.MainActivity;
import com.sample.R;
import com.sample.View.CircleBar;

/**
 * Created by xiaoye on 2016/4/29.
 */
public class CircleBarFragment extends BaseBackStackFragment<MainActivity> {

    private CircleBar progress;
    private TextView lastTime;
    private TextView lastTag;


    @Override
    protected int getContentView() {
        return R.layout.circle_progress_bar_layout;
    }

    @Override
    protected void init() {
        super.init();

        initProgress(660);
    }

    private void initProgress(int min) {

        progress = (CircleBar) getView().findViewById(R.id.progress);
        lastTime = (TextView) getView().findViewById(R.id.day);
        lastTag = (TextView) getView().findViewById(R.id.tag);

        int hour = min / 60;

        //初始化显示时间
        if (hour < 24) {
            lastTime.setText(hour + "");
            lastTag.setText("小时");
        } else {
            int day = hour / 24;
            lastTime.setText(day + "");
            lastTag.setText("天");
        }

        //初始化进度条
        progress.update(hour, 3000);
    }
}
