package com.library.Adapter;

import android.support.annotation.CallSuper;
import android.view.View;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ListViewHolder<Data, Adapter extends CustomAdapter>  {

    public final View itemView;
    protected Adapter mAdapter;
    protected Data currData;
    protected int position;

    public ListViewHolder(View item, Adapter adapter) {
        mAdapter = adapter;
        itemView = item;
    }

    @CallSuper
    public void bindData(int position) {
        this.position = position;
        currData = (Data) mAdapter.getAdapterDataManager().getData(position);
    }

}
