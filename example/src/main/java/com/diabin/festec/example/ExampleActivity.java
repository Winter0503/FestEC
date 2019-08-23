package com.diabin.festec.example;


import com.diabin.latte.activitys.ProxyActivity;
import com.diabin.latte.delegate.LatteDelegate;
import com.diabin.latte.ec.launcher.LauncherDelegate;
import com.diabin.latte.ec.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }

}
