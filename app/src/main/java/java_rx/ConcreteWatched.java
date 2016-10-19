package java_rx;


import java.util.ArrayList;
import java.util.List;

/**
 * Descriptions：被观察者的实现
 * <p>
 * Author：ChenME
 * Date：10/19/2016
 * Email：ibelieve1210@163.com
 */
public class ConcreteWatched implements Watched {

    private List<Watcher> list = new ArrayList<>();

    @Override
    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    @Override
    public void notifyWatchers(String msg) {
        for (Watcher watcher : list) {
            watcher.update(msg);
        }
    }
}
