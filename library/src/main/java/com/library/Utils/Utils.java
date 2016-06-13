package com.library.Utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiangzhihong on 2015/12/3 on 18:13.
 */
public class Utils {
    private static int width;
    private static int height;



    @TargetApi(19)
    public static void setTranslucentStatus(boolean on, Activity context) {
        Window win = context.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }



    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        } else {
            return null;
        }
    }

    public static int getScreenWidth(Activity context) {
        if (width != 0) {
            return width;
        }
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        return width;
    }

    public static int getScreenHeight(Activity context) {
        if (height != 0) {
            return height;
        }
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        height = dm.heightPixels;
        return height;
    }

    public static Drawable getCompoundDrawable(@NonNull Context context, @DrawableRes int resId) {
        return getCompoundDrawable(context, resId, 1);
    }

    public static Drawable getCompoundDrawable(@NonNull Context context, @DrawableRes int resId, float scale) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, (int) (drawable.getMinimumWidth() * scale), (int) (drawable.getMinimumHeight() * scale));
        return drawable;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 时间格式化
     *
     * @param str
     * @return
     */
    public static Date toDate(String str) {
        String pattern = null;
        SimpleDateFormat format = null;
        try {
            if (str.contains(".")) {
                if (str.contains(" ") && str.contains(":"))
                    pattern = "yyyy.MM.dd HH:mm:ss";
                else
                    pattern = "yyyy.MM.dd";
            } else if (str.contains("-")) {
                if (str.contains(" ") && str.contains(":"))
                    if (str.split(":").length > 1) {
                        pattern = "yyyy-MM-dd HH:mm";
                    } else
                        pattern = "yyyy-MM-dd HH:mm:ss";
                else {
                    pattern = "yyyy-MM-dd";
                }
            } else if (str.contains("/")) {
                if (str.contains(" ") && str.contains(":"))
                    pattern = "yyyy/MM/dd HH:mm:ss";
                else
                    pattern = "yyyy/MM/dd";
            } else if (str.contains(":") && str.split(":").length > 1) {
                pattern = "yyyy年MM月dd日 HH:mm:ss";
            } else {
                long milliseconds = Long.parseLong(str);
                return new Date(milliseconds * 1000);
            }
            format = new SimpleDateFormat(pattern);
            return format.parse(str);
        } catch (Exception e) {
            return new Date();
        }
    }

    public static String getFromAssets(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();
        String strResponse = "";
        try {
            InputStream ims = assetManager.open(fileName);
            strResponse = getStringFromInputStream(ims);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResponse;
    }

    private static String getStringFromInputStream(InputStream a_is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(a_is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return sb.toString();
    }

    public static void setDrawableTop(TextView textView, Drawable drawable) {
        Drawable mDrawable = drawable;
        drawable.setBounds(0, 0, mDrawable.getMinimumWidth(), mDrawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

}
