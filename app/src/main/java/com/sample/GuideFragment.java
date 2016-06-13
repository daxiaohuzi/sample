package com.sample;

import android.view.View;
import android.widget.Toast;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.sample.Fragment.View.Bezier3Fragment;
import com.sample.Fragment.View.CircleBarFragment;
import com.sample.RxJava.RxJavaTestFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoye on 2016/4/24.
 */
public class GuideFragment extends BaseBackStackFragment<MainActivity> {
    @Override
    protected int getContentView() {
        return R.layout.guide_layout;
    }

    @Override
    protected void init() {
        super.init();
        ButterKnife.bind(this, getView());
    }

    @OnClick({R.id.jump_rxjava, R.id.jump_viewPager, R.id.jump_taobao_anim, R.id.jump_statusBar,
            R.id.jump_circleBar, R.id.jump_Bezier, R.id.jump_Bezier3, R.id.jump_stack
            , R.id.jump_simpleRecyclerFragment, R.id.jump_custom_view, R.id.jump_tabLayout})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.jump_stack:
                getSelftActivity().addFragment(new twoFragment());
                break;

            case R.id.jump_rxjava:
                getSelftActivity().addFragment(new RxJavaTestFragment());
                break;

            case R.id.jump_viewPager:
                getSelftActivity().addFragment(new CircleViewPagerFragment());
                break;

            case R.id.jump_taobao_anim:
                getSelftActivity().addFragment(new Anim_TaobaoFragment());
                break;


            case R.id.jump_Bezier:
                getSelftActivity().addFragment(new BezierFragment());
                break;

            case R.id.jump_circleBar:
                getSelftActivity().addFragment(new CircleBarFragment());
                break;
            case R.id.jump_Bezier3:
                getSelftActivity().addFragment(new Bezier3Fragment());
                break;
            case R.id.jump_simpleRecyclerFragment:
                getSelftActivity().addFragment(new SimpleRecyclerFragment());
                break;
            case R.id.jump_statusBar:
                getSelftActivity().addFragment(new CustomNavigationBarFragment());
                break;
            case R.id.jump_custom_view:
                getSelftActivity().addFragment(new CustomViewFragment());
                break;
            case R.id.jump_tabLayout:
                getSelftActivity().addFragment(new TabLayoutFragment());
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Toast.makeText(getActivity(), "GuideFragment " + (hidden ? "隐藏" : "显示"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Toast.makeText(getActivity(), "回到GuideFragment", Toast.LENGTH_SHORT).show();
        }
    }
}
