package com.diabin.latte.ui.launcher;

import android.content.Context;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Create by 心跳 on 2019/8/23 11:56
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mImageView = null;


    @Override
    public View createView(Context context) {
        mImageView =new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
