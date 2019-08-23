package com.diabin.festec.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.diabin.latte.delegate.LatteDelegate;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.IError;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.ISuccess;


/**
 * Create by 心跳 on 2019/8/16 15:57
 * Blog : https://mp.csdn.net/
 * escription :
 * @author 心跳
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        testRestClient();
    }

    @Override
    public Object setLayout() {
        return com.diabin.festec.example.R.layout.delegate_example;
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index")
//                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }

}
