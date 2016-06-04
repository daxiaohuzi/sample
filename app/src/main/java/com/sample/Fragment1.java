package com.sample;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.library.Activity.Fragment.BaseBackStackFragment;

/**
 * Created by xiaoye on 2016/4/11.
 */
public class Fragment1 extends BaseBackStackFragment<MainActivity> implements View.OnClickListener {
    @Override
    protected void init() {
        super.init();
        getView().findViewById(R.id.nextFragment).setOnClickListener(this);
        ((TextView) getView().findViewById(R.id.name)).setText("Fragment1");
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
    public boolean isReceiveData() {
        return true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Toast.makeText(getActivity(),"Fragment1 "+(hidden?"隐藏":"显示"),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextFragment:
                getSelftActivity().addFragment(new Fragment2());
                break;
        }
    }

    @Override
    public boolean receiveData(Object data) {
        if(data instanceof Integer){
            Toast.makeText(getActivity(),"Fragment1 接受到数字消息"+data,Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.receiveData(data);
    }

    @Override
    public boolean receiveData(String tag,Object data) {
        if("fragment1".equals(tag)){
            Toast.makeText(getActivity(),"Fragment1 接受到字符串消息 "+data,Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.receiveData(data);
    }
}
