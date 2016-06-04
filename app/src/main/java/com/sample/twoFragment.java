package com.sample;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.library.Activity.Fragment.BaseBackStackFragment;

/**
 * Created by xiaoye on 2016/4/11.
 */
public class twoFragment extends BaseBackStackFragment<MainActivity> implements View.OnClickListener {
    @Override
    protected void init() {
        super.init();
        getView().findViewById(R.id.nextFragment).setOnClickListener(this);
        ((TextView) getView().findViewById(R.id.name)).setText("TwoFragment");
        getView().findViewById(R.id.image1).setOnClickListener(this);
        getView().findViewById(R.id.image2).setOnClickListener(this);
        getView().findViewById(R.id.image3).setOnClickListener(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.two;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Toast.makeText(getActivity(),"TwoFragment "+(hidden?"隐藏":"显示"),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onShow() {

    }

    @Override
    protected void onHide() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextFragment:
                getSelftActivity().addFragment(new Fragment1());
                break;
            case R.id.image1:
                System.out.println("Image1");
                break;
            case R.id.image2:
                System.out.println("Image2");
                break;
            case R.id.image3:
                System.out.println("Image3");
                break;
        }
    }

    @Override
    public boolean isReceiveData() {
        return true;
    }

    @Override
    public boolean receiveData(Object data) {
        if(data instanceof Integer){
            Toast.makeText(getActivity(),"TwoFragment 接受到数字消息"+data,Toast.LENGTH_SHORT).show();
            return true;
        }else if(data instanceof String){
            Toast.makeText(getActivity(),"TwoFragment 接受到字符串消息"+data,Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.receiveData(data);
    }

    @Override
    public boolean receiveData(String tag,Object data) {
        if("two".equals(tag)){
            Toast.makeText(getActivity(),"TwoFragment 接受到字符串消息 "+data,Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.receiveData(data);
    }
}
