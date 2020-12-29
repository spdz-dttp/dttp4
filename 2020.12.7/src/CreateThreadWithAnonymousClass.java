/**
 * @program: 2020.12.7
 * @description: 多线程4
 * @author: spdz
 * @create: 2020-12-06 15:37
 **/

/**
 * 通过匿名类 或 Lambda 表达式 创建线程
 */

/**
 *匿名类
 *  new 要继承的类名称/要实现的接口名（构造方法）{
 *      @Override
 *      覆写的方法。。
 *  }
 *
 *  构造一个类，该类是匿名的，并且是已知类 的子类，同时，创建该类的对象
 *  这个类是一次性的
 */

/**
 * Lambda 可以认为是 匿名类 的变形（不全面）
 */

public class CreateThreadWithAnonymousClass {
    public static void main(String[] args) {

        //1.匿名类 创建 线程，是一次性的
        Thread thread = new Thread() {
            @Override
            public void run(){
                while (true) {
                    System.out.println("子线程A");
                }
            }
        };
        thread.start();

        //Lambda 创建线程
        Thread thread2 = new Thread(() -> {
            while (true) {
                System.out.println("子线程B");
            }
        });
        thread2.start();

        while (true) {
            System.out.println("主线程");
        }//线程 执行 顺序 的随机性
    }
}
