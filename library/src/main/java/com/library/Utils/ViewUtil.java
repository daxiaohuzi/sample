package com.library.Utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2016/3/16.
 */
public class ViewUtil {

    public static View createViewByID(Context context,@LayoutRes int id){
        LayoutInflater factory = LayoutInflater.from(context);
        return factory.inflate(id, null);
    }
}
