package com.diabin.latte.net;

import android.content.Context;

import com.diabin.latte.net.callback.IError;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.IRequest;
import com.diabin.latte.net.callback.ISuccess;
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
public class RestClientBuilder {
    private String mUrl=null;
//    private  static final Map<String,Object> PARAMS = RestCreator.PARAMS;
    private static Map<String, Object> PARAMS=RestCreator.getParams();
    private IRequest mIRequest=null;
    private ISuccess mISuccess=null;
    private IFailure mIFailure=null;
    private IError mIError=null;
    private RequestBody mBody=null;
    private Context mContext=null;
    private File mFlie=null;
    private LoaderStyle mLoaderStyle=null;

    private String mDwonloadDir=null;
    private String mExtension=null;
    private String mName=null;

    RestClientBuilder(){

    }
    private Map<String, Object> checkParams(){
        return PARAMS;
    }

    public final RestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }

    public final RestClientBuilder params(Map<String, Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }
    public final RestClientBuilder file(File file){
        this.mFlie=file;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.mFlie=new File(file);
        return this;
    }

    public final RestClientBuilder dir(String dir){
        this.mDwonloadDir=dir;
        return this;
    }
    public final RestClientBuilder extension(String extension){
        this.mExtension=extension;
        return this;
    }



    public final RestClientBuilder raw(String raw){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UIF-8"),raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mIRequest=iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess=iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure){
        this.mIFailure=failure;
        return this;
    }

    public final RestClientBuilder error(IError iError){
        this.mIError=iError;
        return this;
    }
    public final RestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext=context;
        this.mLoaderStyle=style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoaderStyle=LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,
                mDwonloadDir,mExtension,mName,
                mIRequest,mISuccess,mIError,mIFailure,
                mBody,mLoaderStyle,mFlie,mContext);
    }
}
