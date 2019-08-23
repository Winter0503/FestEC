package com.diabin.latte.util.timer;

import java.util.TimerTask;

/**
 * Create by 心跳 on 2019/8/20 20:28
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class BaseTimerTask extends TimerTask {

    ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener){
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if(mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
