package com.diabin.latte.ec.launcher;

import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.diabin.latte.delegate.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ui.launcher.LauncherHolderCreator;
import com.diabin.latte.util.LattePreference;

import java.util.ArrayList;

/**
 *  加载页 轮播
 */
public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private final ArrayList<Integer> INTEGERS = new ArrayList<>();
//    private ILauncherListener mILauncherListener = null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})  //
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) // 设置底下焦点的位置
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof ILauncherListener) {
//            mILauncherListener = (ILauncherListener) context;
//        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            //第一次进入
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录
//            AccountManager.checkAccount(new IUserChecker() {
//                @Override
//                public void onSignIn() {
//                    if (mILauncherListener != null) {
//                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
//                        getSupportDelegate().pop();
//                    }
//                }
//
//                @Override
//                public void onNotSignIn() {
//                    if (mILauncherListener != null) {
//                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
//                        getSupportDelegate().pop();
//                    }
//                }
//            });
        }
    }
}
