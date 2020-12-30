/**
 * @program: 2020.12.8
 * @description: 多线程5
 * @author: spdz
 * @create: 2020-12-07 17:07
 **/

/**
 * 线程的常见方法-- Thread 的常见方法
 *  1.属性相关
 *      id: JVM内部，每个线程的唯一 标识.
 *      name: 线程的名字，为了打印日志、调试使用，可以重复
 *      优先级: 变相地影响到线程被调度到的机会( 建议型的属性,不是一定 按照优先级走)
 *      状态: 类似 进程调度时 的进程状态
 *      是否存活: 结合状态理解
 *      是否是后台线程: 什么情况下JVM会退出
 */

/**
 *  1.Thread 实现了Runnable
 *  2.一个JVM 应用中允许有多个线程同时运行，
 *    其中，运行 main方法 的最开始的线程，是一个非后 台线程，一般称为主线程
 *  3.任何线程 都是从经由 一个线程 执行代码 创建出来的，习惯称其为 父子线程
 *  4.优先级 和 后台线程
 *      新线程的优先级 最初设置为 等于 创建线程的优先级，
 *      并且当且仅当 创建线程是 后台线程时 才是后台线程
 *  5.什么时候，JVM 可以退出
 *      1.调用了 System.exit()
 *      2.所有 非后台线程 退出，JVM 就可以退出了
 */

//调试 多线程 ---> jdk\bin ---> jconsole.exe

//打印线程属性
public class PrintThreadFields {

    static class SubTread extends Thread {

        SubTread(String s) {
            super(s);
        }

        public void run() {
            try {
                printFields();
                Thread.sleep(100_000);//休眠100秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new SubTread("陆任佳");
        Thread t2 = new SubTread("江由衣");
        Thread t3 = new SubTread("陆任佳");// name 可以 相同

        t1.start();
        t2.start();
        t3.start();

        printFields();


        Thread.sleep(100_000);//休眠100秒
    }

    private static void printFields() {
        //Thread.currentThread(); 返回 当前线程 的引用
        Thread t = Thread.currentThread();
        long id = t.getId();
        System.out.println("线程的名字：" + id + ": " + t.getName());
        System.out.println("线程的优先级：" + id + "：" + t.getPriority());
        System.out.println("线程的状态：" + id + ": " + t.getState());
        System.out.println("线程是否存活：" + id + ": " + t.isAlive());
        System.out.println("线程是否是后台线程：" + id + ": " + t.isDaemon());
    }
}
