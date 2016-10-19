package java_rx2;


import java.util.Observable;
import java.util.Observer;

/**
 * Descriptions：观察者
 * <p>
 * Author：ChenME
 * Date：10/19/2016
 * Email：ibelieve1210@163.com
 */
public class SimpleObserver implements Observer {

    public SimpleObserver(SimpleObservable observable) {
        observable.addObserver(this);
    }

    /**
     * 当被观察者状态发生时会调用
     * @param observable
     * @param obj
     */
    public void update(Observable observable, Object obj) {
        System.out.println("data is changed:" + ((SimpleObservable) observable).getData());
    }
}
