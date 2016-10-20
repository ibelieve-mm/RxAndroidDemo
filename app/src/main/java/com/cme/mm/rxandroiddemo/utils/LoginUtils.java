package com.cme.mm.rxandroiddemo.utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Descriptions：登陆的工具类
 * <p>
 * Author：ChenME
 * Date：10/20/2016
 * Email：ibelieve1210@163.com
 */
public class LoginUtils {

    private OkHttpClient client;

    public LoginUtils() {
        client = new OkHttpClient();
    }

    public Observable<String> login(String url, Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    FormBody.Builder builder = new FormBody.Builder();
                    if (null != params && !params.isEmpty()) {
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                            builder.add(entry.getKey(), entry.getValue());
                        }
                    }
                    RequestBody requestBody = builder.build();

                    //构建post请求
                    Request request = new Request.Builder().url(url).post(requestBody).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                subscriber.onNext(response.body().string());
                            }
                            subscriber.onCompleted();
                        }
                    });

                }
            }
        });
    }

    public Observable<String> login(String url, String aes) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    FormBody.Builder builder = new FormBody.Builder();
                    if (null != aes) {
                        builder.add("info", aes);
                    }
                    RequestBody requestBody = builder.build();

                    //构建post请求
                    Request request = new Request.Builder().url(url).post(requestBody).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                subscriber.onNext(response.body().string());
                            }else{
                                subscriber.onNext("访问网络失败~~"+response.toString());
                            }
                            subscriber.onCompleted();
                        }
                    });

                }
            }
        });
    }
}
