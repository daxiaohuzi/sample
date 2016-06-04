package com.sample.Test.Fragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiaoye on 2016/6/1.
 */
public class Demo extends Test<Test> {
    public Demo(String string) {
        super(string);
    }

    public Demo(String string, Date date) {
        super(string, date);
    }

    public static void main(String[] args) {

        Class vhClass = (Class) ((ParameterizedType) Demo.class.getGenericSuperclass()).getActualTypeArguments()[0];
        if (vhClass != null) {
            Constructor<?> constructors = null;
            try {
                constructors = vhClass.getConstructor(String.class, Date.class);
                constructors.newInstance("测试", Calendar.getInstance().getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
