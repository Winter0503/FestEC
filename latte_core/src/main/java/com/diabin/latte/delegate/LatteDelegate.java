package com.diabin.latte.delegate;

/**
 * Create by 心跳 on 2019/8/16 13:08
 * Blog : https://mp.csdn.net/
 * escription :
 */
public  abstract  class LatteDelegate extends PremissionCheckerDelegate{


    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
