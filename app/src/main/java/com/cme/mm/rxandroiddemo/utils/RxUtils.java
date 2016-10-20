package com.cme.mm.rxandroiddemo.utils;

import com.cme.mm.rxandroiddemo.App;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Descriptions：熟悉调用RxAndroid中常用的方法
 * <p>
 * Author：ChenME
 * Date：10/19/2016
 * Email：ibelieve1210@163.com
 */
public class RxUtils {

    /**
     * 模拟下载の方法
     *
     * @return
     */
    public static String downLoadJson() {
        return "Json Data";
    }

    /**
     * 第一种方式（实际开发中最常用的）
     */
    public static void createObservable() {
        //定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
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
                App.log("创建方式1——>>" + "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log("创建方式1——>>" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                App.log("创建方式1——>>" + s);
            }
        };

        //关联被观察者
        observable.subscribe(showSub);
    }


    /**
     * 第二种方式
     */
    public static void createPrint() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 1; i < 10; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                App.log("创建方式2——>>" + "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log("创建方式2——>>" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                App.log("创建方式2——>>" + integer);
            }
        });
    }

    /**
     * 使用在被观察者返回的对象一般都是数值类型
     */
    public static void from() {
        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Observable observable = Observable.from(items);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                App.log("from——>>" + o.toString());
            }
        });
    }

    /**
     * 指定某一时刻进行数据发送
     */
    public static void interval() {
        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Observable observable = Observable.interval(1, 1, TimeUnit.SECONDS);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                App.log("interval——>>" + o);
            }
        });
    }

    /**
     * 处理数组集合,依次输出两个集合
     */
    public static void just() {
        Integer[] items1 = {1, 2, 3, 4, 5};
        Integer[] items2 = {6, 7, 8, 9};
        Observable observable = Observable.just(items1, items2);
        observable.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                App.log("just——>>" + "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log("just——>>" + e.getMessage());
            }

            @Override
            public void onNext(Integer[] o) {
                for (int i = 0; i < o.length; i++) {
                    App.log("just——>>" + o[i]);
                }
            }
        });
    }

    /**
     * 指定输出数据的范围
     */
    public static void range() {
        String[] arr = {"1.java", "2.OC", "3.c++", "4.JavaScript", "5.php", "6.C#"};
        Observable observable = Observable.range(5, 3);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                App.log("range——>>" + "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log("range——>>" + e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                App.log("range——>>" + o);
            }
        });
    }

    /**
     * 使用过滤功能
     */
    public static void filter() {
        Observable observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer o) {
                return o < 5;
            }
        }).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                App.log("filter——>>" + "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log("filter——>>" + e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                App.log("filter——>>" + o.toString());
            }
        });
    }
}