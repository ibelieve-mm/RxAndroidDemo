package java_rx2;

import java.util.Observable;

/**
 * Descriptions：被观察者
 * <p>
 * Author：ChenME
 * Date：10/19/2016
 * Email：ibelieve1210@163.com
 */
public class SimpleObservable extends Observable {

    private int data = 0;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        if (this.data != data) {
            this.data = data;
            setChanged();//发生改变
            notifyObservers();//通知观察者，表示状态发生改变
        }
    }
}