package com.diabin.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.diabin.latte.app.Latte;

/**
 * Create by 心跳 on 2019/8/19 16:05
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class DimenUtil {

    public static int getScreenWindth(){
        final Resources resources= Latte.getApplicationContext().getResources();
        final DisplayMetrics dm= resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources= Latte.getApplicationContext().getResources();
        final DisplayMetrics dm= resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
