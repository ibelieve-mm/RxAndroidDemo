package com.cme.mm.rxandroiddemo.utils;

import com.cme.mm.rxandroiddemo.App;

import rx.Observable;
import rx.Subscriber;

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：10/19/2016
 * Email：ibelieve1210@163.com
 */
public class RxUtils {

    private final String TAG = RxUtils.class.getSimpleName();

    public static void creatObservable() {
        //定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    subscriber.onNext("Hello");
                    subscriber.onNext("Hi");
                    subscriber.onNext(downLoadJson());
                    subscriber.onNext("World");
                    subscriber.onCompleted();
                }
            }
        });

        Subscriber<String> showSub = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                App.log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                App.log("输出结果-->>"+s);
            }
        };

        //关联被观察者
        observable.subscribe(showSub);
    }

    public static String downLoadJson() {
        return "Json Data";
    }
}