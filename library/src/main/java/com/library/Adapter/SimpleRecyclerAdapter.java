package com.library.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;

/**
 * Created by xiaoye on 2016/6/1.
 */
public abstract class SimpleRecyclerAdapter<Data, Owner> extends RecyclerAdapter<Data, SimpleRecyclerViewHolder, Owner> {

    private SparseArray<Constructor> mTypes = new SparseArray<>(2);

    public SimpleRecyclerAdapter(Context context, Owner owner) {
        super(context, owner);
    }

    public SimpleRecyclerAdapter addViewHolder(Class clazz) {
        addViewHolderType(clazz);
        return this;
    }

    void addViewHolderType(Class clazz) {
        try {
            Constructor constructors = clazz.getConstructor(View.class, SimpleRecyclerAdapter.class);
            int type = ((SimpleRecyclerViewHolder) constructors.newInstance(new View(mContext), this)).getType();
            mTypes.put(type, constructors);
        } catch (Exception e) {
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            Constructor constructor = mTypes.get(viewType);
            return (SimpleRecyclerViewHolder) constructor.newInstance(createView(viewType), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {
        return mTypes.keyAt(getViewHolderIndex(position));
    }

    /**
     * 获取ViewHolder的下标
     */
    protected abstract int getViewHolderIndex(int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SimpleRecyclerViewHolder) holder).bindData(position);
    }


}
