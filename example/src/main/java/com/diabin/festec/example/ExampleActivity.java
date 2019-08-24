package com.diabin.festec.example;


import android.os.Bundle;

import androidx.appcompat.app.ActionBar;

import com.diabin.latte.activitys.ProxyActivity;
import com.diabin.latte.delegate.LatteDelegate;
import com.diabin.latte.ec.launcher.LauncherDelegate;
import com.diabin.latte.ec.launcher.LauncherScrollDelegate;
import com.diabin.latte.ec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignUpDelegate();
    }

}
