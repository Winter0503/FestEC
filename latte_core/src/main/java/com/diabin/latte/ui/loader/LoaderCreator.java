package com.diabin.latte.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Create by 心跳 on 2019/8/18 19:43
 * Blog : https://mp.csdn.net/
 * escription :
 */
public final class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADING_MAP=new WeakHashMap<>();
    static AVLoadingIndicatorView creare(String type , Context context){

        final AVLoadingIndicatorView avLoadingIndicatorView= new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type) == null){
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name){

        //com.wang.avi.indicators.BallPulseIndicator
        if(name==null||name.isEmpty()){
            return null;
        }
        final StringBuilder drawablePackageName = new StringBuilder();
        if(!name.contains(".")){
            final String defaultPackegName= AVLoadingIndicatorView.class.getPackage().getName();
            drawablePackageName.append(defaultPackegName)
                    .append(".indicators")
                    .append(".");
        }
        drawablePackageName.append(name);
        try {
            final Class<?> drawablecalss= Class.forName(drawablePackageName.toString());
            return (Indicator) drawablecalss.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
