/**
 * @program: 2020.12.6
 * @description: 多线程3
 * @author: spdz
 * @create: 2020-12-06 12:20
 **/

/**
 * 创建java 线程过程中 比较重要的 两个概念
 *  代表线程的类 -- class Thread
 *  代表任务的类 -- interface Runnable
 *
 *  Thread 还实现了 Runnable 接口
 *  一个 Thread 类对象，也可以视为 Runnable 对象
 */

/**
 * Java语言中，创建线程的基本方法(本质核心都需要有一个Thread 对象出来)
 *  1.创建-一个Thread对象 + 关联任务
 *      1.继承一个Thread 类，并且覆写（Runnable类的）run方法（覆写了线程的任务）
 *        new该类的对象.
 *      2.实现Runnable的一个类，并且覆写（Runnable类的）run 方法（覆写了任务）
 *        new Thread (创建任务对象)
 *      3.继承一个Thread类，覆写（Runnable类的）run方法
 *        把该类的对象当作Runnable 对象处理
 *  2.把Thread对象对应的线程加入到就绪队列中(随时可以被调度开始执行)
 *      线程对象.start();
 *          把线程加入 就绪队列中，等待调度
 *          这里是start 不是run
 *          一个线程对象 只允许 调用 一次 start();
 *          这里结束之后，才真正意义上出现一个调度单位（线程）！
 */

public class HowToInstanceThread {

    static class A extends Thread {
        public void run() {
            System.out.println("A");
        }
    }

    static class B implements Runnable {
        public void run() {
            System.out.println("B");
        }
    }

    public static void main(String[] args) {
        {
            // 1. 直接 new A 类的对象，本身就是一个 Thread 对象
            Thread thread = new A();
            thread.start();
        }

        {
            // 2. new B 类的对象，是一个 Runnable，作为任务传递给线程对象
            B b = new B();//相当于 Runnable b = new B();
            //Thread thread = b; 这个不能指向
            Thread thread = new Thread(b);
            thread.start();
        }

        {
            // 3. new A 类的对象，但把该对象，当作 Runnable 使用
            // 因为 Thread 本来就实现了 Runnable
            A a = new A();//相当于 Runnable a = new A();
            Thread thread = new Thread(a);
            thread.start();
        }

        {
            A a = new A();
            a.start();
        }
    }
}
