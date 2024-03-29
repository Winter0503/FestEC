package com.diabin.latte.ec.sign;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.diabin.latte.app.Latte;
import com.diabin.latte.delegate.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by 心跳 on 2019/8/23 21:17
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;
//    private ISignListener mISignListener = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    //登录按钮触发的事件
    @OnClick(R2.id.btn_sign_in)
    void onClickSign(){
        checkForm();
    }

    //第三方登录的时候触发的事件
    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {
//        LatteWeChat.getInstance()
//                .onSignSuccess(new IWeChatSignInCallback() {
//                    @Override
//                    public void onSignInSuccess(String userInfo) {
//                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
//                    }
//                })
//                .signIn();
    }

    //还没有注册的情况  ，点击还未注册事件
    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }


    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
