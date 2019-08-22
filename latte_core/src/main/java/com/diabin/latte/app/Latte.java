package com.diabin.latte.app;
import android.content.Context;
import android.os.Handler;

import java.util.HashMap;
/**
 * Create by 心跳 on 2019/8/12 16:47
 * Blog : https://mp.csdn.net/
 * escription : 共有的工具类 ，提供静态方法供外部类调用
 */
public final class Latte {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }


    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

    public static void test(){
    }


}
