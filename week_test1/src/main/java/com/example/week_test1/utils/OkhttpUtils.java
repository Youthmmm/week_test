package com.example.week_test1.utils;

import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {
    private static OkhttpUtils okhttpUtils;
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler();

    private OkhttpUtils(){
        okHttpClient = new OkHttpClient.Builder()
                //拦截器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }


    public static OkhttpUtils getInstance() {
        if (okhttpUtils==null){

            synchronized (OkhttpUtils.class){
                if (okhttpUtils==null){

                    okhttpUtils = new OkhttpUtils();
                }
            }

        }
        return okhttpUtils;
    }

    /**
     * get请求
     * @param url
     * @param okhttpCallback
     */
    public void doGet(String url, final OkhttpCallback okhttpCallback){

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (okhttpCallback!=null){
                    okhttpCallback.failure(e);
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //子线程处理网络数据
                final String result = response.body().string();
                if (okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.success(result);
                        }
                    });

                }
            }
        });


    }
    /**
     * post请求
     * @param url
     * @param okhttpCallback
     */
    public void doPost(String url, HashMap<String,String> params, final OkhttpCallback okhttpCallback){


        FormBody.Builder builder = new FormBody.Builder();

        if (params!=null&&params.size()>0){
            for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {

                builder.add(stringStringEntry.getKey(),stringStringEntry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (okhttpCallback!=null){
                    okhttpCallback.failure(e);
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //子线程处理网络数据
                final String result = response.body().string();
                if (okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.success(result);
                        }
                    });

                }
            }
        });


    }

    public interface OkhttpCallback{
        void success(String result);
        void failure(Throwable throwable);
    }
}
