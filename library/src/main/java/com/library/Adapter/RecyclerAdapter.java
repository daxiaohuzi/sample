package com.library.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.library.Adapter.callback.ViewHolderClickListener;

/**
 * Created by Administrator on 2016/3/15.
 */
public abstract class RecyclerAdapter<Data, VH extends RecyclerViewHolder, Owner> extends RecyclerView.Adapter {


    protected final Context mContext;

    /**
     * 拥有者
     */
    private final Owner mAdapterOwner;

    protected AdapterData<Data> mAdapterData;

    private ViewHolderClickListener mClickListener;


    public AdapterData getAdapterDataManager() {
        return mAdapterData;
    }

    public RecyclerAdapter(Context context, Owner owner) {
        mContext = context;
        mAdapterData = new AdapterData();
        mAdapterOwner = owner;
    }

    public void setClickListener(ViewHolderClickListener listener) {
        mClickListener = listener;
    }

    public void requestClick(Data data, int type, int position) {
        if (mClickListener != null) mClickListener.itemClick(data, type, position);
    }

    @Override
    public int getItemCount() {
        return mAdapterData.getCount();
    }

    public View createView(@LayoutRes int id) {
        return View.inflate(mContext, id, null);
    }


}
