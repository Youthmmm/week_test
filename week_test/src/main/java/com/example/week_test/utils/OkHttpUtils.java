package com.example.week_test.utils;

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
import okhttp3.ResponseBody;

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler();

    private OkHttpUtils(){

    }

    public static OkHttpUtils getInstance(){
        return okHttpUtils;
    }

    public void doGet(String url, final OkHttpCallBack okHttpCallBack){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (okHttpCallBack!=null){
                    okHttpCallBack.error(e);
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String result = response.body().string();
                if (okHttpCallBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallBack.success(result);
                        }
                    });
                }
            }
        });
    }
    //dopost
    public void doPost(String url, HashMap<String,String> params, final OkHttpCallBack okHttpCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        if (params!=null&&params.size()>0){
            for (Map.Entry<String,String> stringStringEntry : params.entrySet()){
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
                if (okHttpCallBack!=null){
                    okHttpCallBack.error(e);
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String result = response.body().string();
                if (okHttpCallBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallBack.success(result);
                        }
                    });

                }

            }
        });
    }

    public interface OkHttpCallBack{
        void success(String result);
        void error(Throwable throwable);
    }
}
