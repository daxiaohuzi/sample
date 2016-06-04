package com.library.Adapter;

import android.view.View;

/**
 * Created by xiaoye on 2016/6/1.
 */
public abstract class SimpleRecyclerViewHolder<Data, Adapter extends RecyclerAdapter> extends RecyclerViewHolder<Data, Adapter> {

    public SimpleRecyclerViewHolder(View view, Adapter adapter) {
        super(view, adapter);
    }

    public abstract int getType();


}
