package com.diabin.latte.net.rx;

import android.content.Context;
import android.database.Observable;


import com.diabin.latte.net.HttpMethod;
import com.diabin.latte.net.RestCreator;
import com.diabin.latte.ui.loader.LatterLoader;
import com.diabin.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Create by 心跳 on 2019/8/16 17:42
 * Blog : https://mp.csdn.net/
 * escription :
 */
public class RxRestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    public RxRestClient(String url,
                        Map<String, Object> params,
                        RequestBody body,
                        LoaderStyle loaderStyle,
                        File file,
                        Context context) {
            this.URL = url;
            PARAMS.putAll(params);
            this.BODY = body;
            this.CONTEXT=context;
            this.FILE=file;
            this.LOADER_STYLE=loaderStyle;
    }

    public static RxRestClientBuilder builder(){
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method){
        final RxRestService service=RestCreator.getRxRestService();
        Observable<String> observable=null;

        if(LOADER_STYLE!=null){
            LatterLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch(method){
            case GET:
                observable= service.get(URL,PARAMS);
                break;
            case POST:
                observable=service.post(URL,PARAMS);
                break;
            case POST_RAN:
                observable=service.postRaw(URL,BODY);
                break;
            case PUT:
                observable=service.put(URL,PARAMS);
                break;
            case PUT_RAN:
                observable=service.putRaw(URL,BODY);
                break;
            case DELETE:
                observable=service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody=
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body=
                        MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                observable=service.upload(URL,body);
                break;
           default:
               break;
        }
        return observable;
    }

    public final Observable<String> get(){
        return request(HttpMethod.GET);
    }

    public final Observable<String> post(){
        if(BODY==null){
            return request(HttpMethod.POST);
        }else{
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAN);
        }
    }

    public final Observable<String> put(){

        if(BODY==null){
            return request(HttpMethod.PUT);
        }else{
            if(!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAN);
        }
    }

    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload(){
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download(){
        return RestCreator.getRxRestService().download(URL,PARAMS);
    }
}
