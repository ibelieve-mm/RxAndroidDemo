package java_rx;

/**
 * Descriptions：测试类
 * <p>
 * Author：ChenME
 * Date：10/19/2016
 * Email：ibelieve1210@163.com
 */
public class Test {
    public  static void main(String[] args)throws Exception{
        Watched thief = new ConcreteWatched();

        Watcher police1 = new ConcreteWatcher();
        Watcher police2 = new ConcreteWatcher();
        Watcher police3 = new ConcreteWatcher();
        Watcher police4 = new ConcreteWatcher();
        Watcher police5 = new ConcreteWatcher();

        thief.addWatcher(police1);
        thief.addWatcher(police2);
        thief.addWatcher(police3);
        thief.addWatcher(police4);
        thief.addWatcher(police5);

        thief.notifyWatchers("我要偷东西了！");
    }
}
