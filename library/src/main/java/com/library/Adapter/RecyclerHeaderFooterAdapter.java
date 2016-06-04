package com.library.Adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/3/15.
 */
public abstract class RecyclerHeaderFooterAdapter<VH extends RecyclerViewHolder, Data, Owner> extends RecyclerAdapter<Data, VH, Owner> {

    public static final int TYPE_HEADER = Integer.MIN_VALUE;
    public static final int TYPE_FOOTER = TYPE_HEADER - 1;
    public static final int TYPE_ITEM = 0;

    public HeaderFooterViewHolder _HeaderViewHolder, _FooterViewHolder;

    public RecyclerHeaderFooterAdapter(Context context, Owner owner) {
        super(context, owner);
    }


    private boolean hasFooter, hasHeader;


    //数据itemViewHolder 实现
    public abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);


    protected void setHasHeader() {
        hasHeader = true;
    }

    protected void setHasFooter() {
        hasFooter = true;
    }

    public void notifyHeaderViewHolder() {
        _HeaderViewHolder.notifySelf();
    }

    public <HeaderData> void setHeaderViewHolderData(HeaderData data) {
        _HeaderViewHolder.bindData(data);
    }

    public <FooterData> void setFoolterViewHolderData(FooterData data) {
        _FooterViewHolder.bindData(data);
    }

    public void notifyFooterViewHolder() {
        _FooterViewHolder.notifySelf();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            if (_HeaderViewHolder != null) return _HeaderViewHolder;
            return _HeaderViewHolder = onCreateHeaderViewHolder();
        } else if (viewType == TYPE_FOOTER) {//底部 加载view

            if (_FooterViewHolder != null) return _FooterViewHolder;
            return _FooterViewHolder = onCreateFooterViewHolder();

        } else {
            //数据itemViewHolder
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    /**
     * HeaderViewHolder创建
     */
    public abstract HeaderFooterViewHolder onCreateHeaderViewHolder();

    /**
     * FooterViewHolder创建
     */
    public abstract HeaderFooterViewHolder onCreateFooterViewHolder();

    //正常数据itemViewHolder 实现
    public abstract void onBindItemViewHolder(final VH holder, int position);

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderFooterViewHolder) {
            ((HeaderFooterViewHolder) holder).notifySelf();
        } else {
            if (hasHeader) --position;
            onBindItemViewHolder((VH) holder, position);
        }
    }

    @Override
    public final int getItemViewType(int position) {
        if (position == 0 && hasHeader) {
            return TYPE_HEADER;
        } else if (hasFooter) {

            if (hasHeader) {
                if (--position == super.getItemCount()) {
                    return TYPE_FOOTER;
                }
            } else if (position == super.getItemCount()) return TYPE_FOOTER;


        }
        return getViewTypeByPosition(hasHeader ? --position : position);
    }

    /**
     * 获取Position获取Item类型
     */
    protected int getViewTypeByPosition(int position) {
        return TYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        int count = super.getItemCount();
        if (hasHeader) ++count;
        if (hasFooter) ++count;
        return count;
    }


    public static class HeaderFooterViewHolder<Adapter extends RecyclerHeaderFooterAdapter, Data> extends RecyclerView.ViewHolder {

        RecyclerHeaderFooterAdapter mAdapter;
        Data currEntity;

        public HeaderFooterViewHolder(View itemView, Adapter adapter) {
            super(itemView);
            mAdapter = adapter;
        }


        public void notifySelf() {
            if (currEntity != null) bindData(currEntity);
        }

        @CallSuper
        public void bindData(Data data) {
            currEntity = data;
        }
    }


}
