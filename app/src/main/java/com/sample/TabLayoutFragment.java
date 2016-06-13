package com.sample;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.library.view.widget.CustomLayout.Tab.TabLayout;

/**
 * Created by xiaoye on 2016/4/22.
 */
public class TabLayoutFragment extends BaseBackStackFragment<MainActivity> {
    @Override
    protected int getContentView() {
        return R.layout.tab_layout;
    }

    @Override
    protected void init() {
        super.init();
        TabLayout layout = ((TabLayout) getView().findViewById(R.id.tabLayout));
        layout.addTabItem(layout.createTabItem(R.drawable.tab_community_selector, "动态"));
        layout.addTabItem(layout.createTabItem(R.drawable.tab_cource_selector, "首页"));
        layout.addTabItem(layout.createTabItem(R.drawable.tab_download_selector, "下载"));
        layout.addTabItem(layout.createTabItem(R.drawable.tab_mine_selector, "我的"));
        layout.setSelect(0);
    }
}
