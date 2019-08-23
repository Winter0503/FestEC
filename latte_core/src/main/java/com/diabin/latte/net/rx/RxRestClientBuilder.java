package com.diabin.latte.net.rx;

import android.content.Context;


import com.diabin.latte.net.RestCreator;
import com.diabin.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Create by 心跳 on 2019/8/16 20:55
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class RxRestClientBuilder {
    private String mUrl=null;
//    private  static final Map<String,Object> PARAMS = RestCreator.PARAMS;
    private static final Map<String, Object> PARAMS= RestCreator.getParams();
    private RequestBody mBody=null;
    private Context mContext=null;
    private File mFlie=null;
    private LoaderStyle mLoaderStyle=null;

    RxRestClientBuilder(){

    }
    private Map<String, Object> checkParams(){
        return PARAMS;
    }

    public final RxRestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }

    public final RxRestClientBuilder params(Map<String, Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }
    public final RxRestClientBuilder file(File file){
        this.mFlie=file;
        return this;
    }

    public final RxRestClientBuilder file(String file){
        this.mFlie=new File(file);
        return this;
    }

    public final RxRestClientBuilder raw(String raw){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UIF-8"),raw);
        return this;
    }
    public final RxRestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext=context;
        this.mLoaderStyle=style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoaderStyle=LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build(){
        return new RxRestClient(mUrl,PARAMS, mBody,mLoaderStyle,mFlie,mContext);
    }
}