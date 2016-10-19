package com.cme.mm.rxandroiddemo.utils;

import com.cme.mm.rxandroiddemo.App;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：10/19/2016
 * Email：ibelieve1210@163.com
 */
public class RxUtils {

//    private final String TAG = RxUtils.class.getSimpleName();

    /**
     * 第一种方式
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
                App.log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                App.log("输出结果-->>" + s);
            }
        };

        //关联被观察者
        observable.subscribe(showSub);
    }

    /**
     * 调用下载方法
     *
     * @return
     */
    public static String downLoadJson() {
        return "Json Data";
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
                App.log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log(e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                App.log("输出结果-->>" + integer);
            }
        });
    }

    /**
     * 使用在被观察者返回的对象一般都是数组类型
     */
    public static void from() {
        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Observable observable = Observable.from(items);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                App.log(o.toString());
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
                App.log(o.toString());
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
                App.log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log(e.getMessage());
            }

            @Override
            public void onNext(Integer[] o) {
                for (int i = 0; i < o.length; i++) {
                    App.log("next:" + o[i]);
                }
            }
        });
    }

    /**
     * 指定输出数据的范围
     */
    public static void range() {
        Observable observable = Observable.range(1, 40);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                App.log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log(e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                App.log("next:" + o);
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
                App.log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                App.log(e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                App.log(o.toString());
            }
        });
    }
}