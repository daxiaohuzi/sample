package com.library.Adapter;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/3/15.
 */
public class RecyclerViewHolder<Data, Adapter extends RecyclerAdapter> extends RecyclerView.ViewHolder {

    protected Data currData;
    protected Adapter mAdapter;


    public RecyclerViewHolder(View itemView, Adapter adapter) {
        super(itemView);
        mAdapter = adapter;
    }

    @CallSuper
    public void bindData(int position) {
        currData = (Data) mAdapter.getAdapterDataManager().getData(position);
    }

}
