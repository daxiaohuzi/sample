package com.library.Adapter.callback;

/**
 * Created by Administrator on 2016/3/28.
 */
public interface ViewHolderClickListener<Data> {
    void itemClick(Data data, int type, int position);
}
