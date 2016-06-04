package com.sample.Test.Fragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiaoye on 2016/6/1.
 */
public class Test<T> {

    public Test(String string) {


    }

    public Test(String string, Date date) {
        System.out.println("两个参数  " + string + "  " + date.toString());
    }

}
