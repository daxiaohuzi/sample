package com.sample;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.library.Activity.BaseActivity;
import com.library.Activity.Fragment.BaseBackStackFragment;

/**
 * Created by xiaoye on 2016/4/11.
 */
public class Fragment2 extends BaseBackStackFragment<MainActivity> implements View.OnClickListener {

    private Button sentMsgToTwo;
    private int mIndex;

    @Override
    protected void init() {
        super.init();
        getView().findViewById(R.id.nextFragment).setOnClickListener(this);
        getView().findViewById(R.id.toTwoFragment).setOnClickListener(this);
        getView().findViewById(R.id.toActivity).setOnClickListener(this);
        ((TextView) getView().findViewById(R.id.name)).setText("Fragment2");

        sentMsgToTwo = new Button(getActivity());
        sentMsgToTwo.setOnClickListener(this);
        sentMsgToTwo.setText("发送消息到Two");
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(-1, 160);

        ((ViewGroup) getView()).addView(sentMsgToTwo, params);
    }

    @Override
    protected int getContentView() {
        return R.layout.two;
    }

    @Override
    protected void onShow() {

    }

    @Override
    protected void onHide() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Toast.makeText(getActivity(), "Fragment2 " + (hidden ? "隐藏" : "显示"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        if (sentMsgToTwo == v) {

            switch (mIndex) {
                case 0:
                    getSelftActivity().sentData(88, BaseActivity.ONE);
                    break;

                case 1:
                    getSelftActivity().sentData(66, BaseActivity.ALL);
                    break;

                case 2:
                    getSelftActivity().sentData("two", new Integer(44));
                    break;

                case 3:
                    getSelftActivity().sentData("fragment1", new Integer(55));
                    break;
                case 4:
                    getSelftActivity().sentData("那个谁不像你了", BaseActivity.ALL);
                    break;
            }

            if (mIndex == 4)
                mIndex = 0;
            else mIndex++;
            return;
        }

        switch (v.getId()) {
            case R.id.nextFragment:

                break;
            case R.id.toTwoFragment:
                getSelftActivity().finishFragment(2);
                break;
            case R.id.toActivity:
                getSelftActivity().finishAllFragment();
                break;
        }
    }
}
