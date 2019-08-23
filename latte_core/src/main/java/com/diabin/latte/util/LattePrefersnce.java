package com.diabin.latte.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.diabin.latte.app.Latte;

/**
 * Create by 心跳 on 2019/8/20 19:37
 * Blog : https://mp.csdn.net/
 * escription :  SharedPreferences 的基本使用
 */
public class LattePrefersnce {

    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());

    private static final String APP_PREFERENCES_KEY ="profile";

    private static SharedPreferences getAppPreference(){
        return PREFERENCES;
    }

    private static void setAppProfile(String val){
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY,val)
                .apply();
    }
    public static String getAppProfile(){
        return getAppPreference().getString(APP_PREFERENCES_KEY,null);
    }

    public static JSONObject getAppProfileJson(){
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile(){
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences(){
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key, boolean flag){
        getAppPreference()
                .edit()
                .putBoolean(key,flag)
                .apply();
    }

    public static boolean getAppFlag(String key){
        return getAppPreference()
                .getBoolean(key,false);
    }
}
