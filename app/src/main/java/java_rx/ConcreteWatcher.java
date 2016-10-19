package java_rx;

/**
 * Descriptions：观察者的实现
 * <p>
 * Author：ChenME
 * Date：10/19/2016
 * Email：ibelieve1210@163.com
 */
public class ConcreteWatcher implements Watcher {
    @Override
    public void update(String msg) {
        System.out.println(msg);
    }
}
