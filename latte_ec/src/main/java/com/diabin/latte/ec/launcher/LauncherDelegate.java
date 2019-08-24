package com.diabin.latte.ec.launcher;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.diabin.latte.delegate.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;
import com.diabin.latte.util.LattePreference;
import com.diabin.latte.util.timer.BaseTimerTask;
import com.diabin.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Create by 心跳 on 2019/8/22 20:42
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {


    private Timer mTimer = null;
    private int mCount = 5;
//    private ILauncherListener mILauncherListener = null;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer =null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    //是否展示滚动的启动项
    private void checkIsShowScroll() {
        if(!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else{
            //检查用户是否登录了App
        }
    }
}
