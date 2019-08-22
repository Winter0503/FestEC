package com.diabin.latte.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.ContentFrameLayout;

import com.diabin.latte.R;
import com.diabin.latte.delegate.LatteDelegate;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Create by 心跳 on 2019/8/16 11:06
 * Blog : https://mp.csdn.net/
 * escription :
 */
public abstract class ProxyActivity extends SupportActivity {

    private final SupportActivityDelegate DELEGATE = new SupportActivityDelegate(this);

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer( Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.dalegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            DELEGATE.loadRootFragment(R.id.dalegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        DELEGATE.onDestroy();
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(new DefaultHorizontalAnimator());
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public void onBackPressedSupport() {
        DELEGATE.onBackPressedSupport();
    }
}
