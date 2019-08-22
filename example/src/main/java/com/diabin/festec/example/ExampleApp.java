package com.diabin.festec.example;

import android.app.Application;

import com.diabin.latte.app.Latte;
import com.diabin.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;


/**
 * Create by 心跳 on 2019/8/13 16:23
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .whithApiHost("https://127.0.0.1/")
//                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();

    }
}
