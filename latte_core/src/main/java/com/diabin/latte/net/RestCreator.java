package com.diabin.latte.net;


import com.diabin.latte.app.ConfigKeys;
import com.diabin.latte.app.Latte;
import com.diabin.latte.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Create by 心跳 on 2019/8/16 20:31
 * Blog : https://mp.csdn.net/
 * escription : 内部类Holder
 */
public class RestCreator {

//    public static final WeakHashMap<String ,Object> PARAMS =new WeakHashMap<>();

    private static final class ParamsHolder{
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class  RetrofitHolder{

        private static final String BASE_URL= (String) Latte.getConfigurations()
                .get(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //create
                .build();
    }

    private static final class OKHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER= new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS= Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor(){
            if(INTERCEPTORS!=null ||INTERCEPTORS.isEmpty()){
                for(Interceptor interceptor:INTERCEPTORS){
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder{

        private static final RestService REST_SERVICE= RetrofitHolder.
                RETROFIT_CLIENT.create(RestService.class);

    }

    /**
     * Service接口
     */
    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }





}
