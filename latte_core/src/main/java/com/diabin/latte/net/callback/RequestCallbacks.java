package com.diabin.latte.net.callback;

import android.os.Handler;

import com.diabin.latte.ui.loader.LatterLoader;
import com.diabin.latte.ui.loader.LoaderStyle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Create by 心跳 on 2019/8/17 8:13
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOAD_STYLE;
    private static final Handler HANDLER=new Handler();

    public RequestCallbacks(IRequest request, ISuccess success, IError error, IFailure failure,LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOAD_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else{
            if(ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }

        if(LOAD_STYLE != null){
            stopLoding();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE!=null){
            FAILURE.onFailure();
        }
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        stopLoding();
    }
    private void stopLoding(){
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatterLoader.stopLoading();
            }
        },1000);
    }
}
