package com.diabin.latte.ui.loader;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.diabin.latte.R;
import com.diabin.latte.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Create by 心跳 on 2019/8/19 14:44
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class LatterLoader {

    private static final int LOADER_SIZE = 8;

    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS=new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context, Enum<LoaderStyle> type){
        showLoadingLoader(context,type.name());
    }

    public static void showLoadingLoader(Context context, String type){
        final AppCompatDialog dialog=new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView=LoaderCreator.creare(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceHeight = DimenUtil.getScreenHeight();
        int deviceWeight = DimenUtil.getScreenWindth();

        final Window dialogWindow=dialog.getWindow();

        if(dialogWindow !=null){
            WindowManager.LayoutParams lp= dialogWindow.getAttributes();
            lp.width = deviceWeight/LOADER_SIZE;
            lp.height = deviceHeight/LOADER_SIZE;
            lp.height = lp.height+deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity= Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }
    public static void showLoading(Context contect){
        showLoadingLoader(contect,DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for(AppCompatDialog dialog : LOADERS){
            if(dialog!=null){
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
