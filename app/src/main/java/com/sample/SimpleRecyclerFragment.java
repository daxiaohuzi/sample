package com.sample;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.library.Adapter.SimpleRecyclerAdapter;

/**
 * Created by xiaoye on 2016/6/1.
 */
public class SimpleRecyclerFragment extends BaseBackStackFragment<MainActivity> {

    private RecyclerView mRecyclerView;

    @Override
    protected int getContentView() {
        return R.layout.recycler;
    }

    @Override
    protected void init() {
        super.init();

        mRecyclerView = (RecyclerView) findView(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new SimpleRecyclerAdapter<String,
                SimpleRecyclerFragment>(getActivity(), this) {

            @Override
            protected int getViewHolderIndex(int position) {
                return position % 2;
            }

            @Override
            public int getItemCount() {
                return 20;
            }
        }.addViewHolder(ViewHolder.TextViewHolder.class).addViewHolder(ViewHolder.ImageViewHolder.class));
    }
}
