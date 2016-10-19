package java_rx2;


/**
 * Descriptions：测试类
 * <p>
 * Author：ChenME
 * Date：10/19/2016
 * Email：ibelieve1210@163.com
 */
public class MyTest {
    public  static void main(String[] args)throws Exception{
        SimpleObservable  simpleObservable = new SimpleObservable();
        SimpleObserver simpleObserver = new SimpleObserver(simpleObservable);

        simpleObservable.setData(1);
        simpleObservable.setData(2);
        simpleObservable.setData(2);
        simpleObservable.setData(3);
    }
}
