package com.library.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2016/3/15.
 */
public abstract class CustomAdapter<Data, VH extends ListViewHolder, Owner> extends BaseAdapter {

    private final Context mContext;
    /**
     * 拥有者
     */
    private final Owner mAdapterOwner;

    protected AdapterData<Data> mAdapterData;

    public AdapterData getAdapterDataManager() {
        return mAdapterData;
    }

    public CustomAdapter(Context context, Owner owner) {
        mContext = context;
        mAdapterData = new AdapterData();
        mAdapterOwner = owner;
    }


    @Override
    public int getCount() {
        return mAdapterData.getCount();
    }

    @Override
    public Data getItem(int position) {
        return mAdapterData.getData(position);
    }


    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder holder;
        if (convertView == null) {
            holder = onCreateViewHolder(parent, position);
        } else holder = (ListViewHolder) convertView.getTag();
        holder.bindData(position);
        return holder.itemView;
    }

    public abstract ListViewHolder onCreateViewHolder(ViewGroup parent, int position);

}
