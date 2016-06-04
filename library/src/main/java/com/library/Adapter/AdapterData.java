package com.library.Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public class AdapterData<Data> {

    private List<Data> mDatas = new ArrayList<>();

    public void append(List<Data> data) {
        mDatas = data;
    }

    public void append(Data data) {
        mDatas.add(data);
    }

    public boolean removeData(Data data) {
        return mDatas.remove(data);
    }

    public Data removeData(int position) {
        if (position >= 0 && position < mDatas.size())
            return mDatas.remove(position);
        return null;
    }

    public void clear() {
        mDatas.clear();
    }

    /**
     * 重新填充数据
     */
    public void resetData(List<Data> data) {
        clear();
        mDatas.addAll(data);
    }


    public List<Data> getDatas() {
        return mDatas;
    }

    public boolean existData() {
        return mDatas.size() > 0;
    }

    public int getCount() {
        return mDatas.size();
    }

    public Data getData(int position) {
        if (mDatas.size() > 0)
            return mDatas.get(position);
        return null;
    }
//    protected Context mContext;
}
