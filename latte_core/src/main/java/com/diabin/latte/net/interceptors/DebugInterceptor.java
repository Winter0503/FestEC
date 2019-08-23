package com.diabin.latte.net.interceptors;


import androidx.annotation.RawRes;

import com.diabin.latte.util.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Create by 心跳 on 2019/8/20 15:23
 * Blog : https://mp.csdn.net/
 * escription : 调试
 */
public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;


    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    /**
     *  获取文件
     * @param chain
     * @param json
     * @return
     */
    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                //调试 json
                .addHeader("Content-Type", "application/json")
                //请求体
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                //目前都是1.1， 2.0 还没有普及
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    /**
     *
     * @param chain
     * @param rawId  为了检查代码的正确性，利用了元注解 @RawRes 来自android注解库的元注解，用来说明这个传入的
     *               int值必须在R上有唯一的id，也就是在res目录下的资源文件的id
     *
     * @return
     */
    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }

}
