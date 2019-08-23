package com.diabin.latte.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Create by 心跳 on 2019/8/20 15:09
 * Blog : https://mp.csdn.net/
 * escription :
 */
public abstract class BaseInterceptor implements Interceptor {
    /**
     *
     * @param chain
     * @return  get请求特有的方法,用来获取 --> 有序的url参数段
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    /**
     *  重载方法
     * @param chain
     * @param key
     * @return
     */
    protected String getUrlParameter(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     *  获取参数的方法 ，从POST请求中
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {

        //FormBody OKHTTP3特有的类
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = 0;
        if (formBody != null) {
            size = formBody.size();
        }
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    /**
     *  重载方法
     * @param chain
     * @param key
     * @return
     */
    protected String getBodyParameter(Chain chain, String key) {
        return getBodyParameters(chain).get(key);
    }
}
