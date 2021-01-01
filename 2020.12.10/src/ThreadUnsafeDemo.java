/**
 * @program: 2020.12.10
 * @description: 多线程7--线程不安全
 * @author: spdz
 * @create: 2020-12-07 20:02
 **/

/**
 * 线程不安全
 *  原因：1.java 中的一条语句，对应的不一定是一条字节码，更不一定是一条 CPU 指令
 *        2.线程的调度 是随机的
 *        （执行一条语句，CPU 可能会执行多条指令，当指令执行了一部分时，发生了调度，就可能出现 线程不安全）
 *
 *  什么情况下会出现 线程 不安全?
 *      1.线程之间有共享的数据的。 换言之，线程之间不存在共享，天生是线程安全的。
 *      2.即使线程之间有共享，但 没有线程 修改 共享数据，则天生是 线程安全的。
 *
 */

/**
 *JVM的运行时内存区域中，哪些位置是共享的?哪些是线程内部私有的?
 * 1.nextPC值--PC区域        私有的
 * 2.栈区(Java栈/native栈)   私有的
 * 3.堆区                    共享的
 * 4.方法区                  共享的
 * 5.运行时常量池            共享的
 *
 *  局部变量  --- 栈帧--栈中
 *  对象的属性  --- 对象---堆中
 *  类的静态属性  --- 类 ---方法区
 *
 *  局部变量 是 线程私有 的数据，不会共享，不需要 特别考虑 线程安全问题。
 *  属性/对象+ 静态属性/类，是共享的，需要考虑 线程安全问题。
 *
 *  线程 之间 修改 静态属性/同一个对象的属性 时，需要解决线程安全问题
 */

/**
 * 线程不安全的三个重要知识点（看板书 4.多线程、5.多线程）(重点)
 *  1.原子性
 *      不可再分割
 *  2.内存可见性
 *      CPU 内 增加了 高速缓存（Cache）
 *      Java 内存模型（JMM）规定：
 *          线程 在操作数据时，先把数据 从主内存 加载到 线程自己的 工作内存
 *
 *      线程调度时，数据 因还留在 高速缓存/工作内存，
 *      导致再次从 主内存 读取到的信息并不是最新的
 *  3.代码重排序
 *      程序员编写 --> 编译器编译 --> JVM (JIT（对 .class 文件再编译） --> CPU)
 *          编译器编译、JIT、CPU 都可以进行重排序
 *          重排序后，执行效率一般更高
 *
 *      重排序要求：单线程时，重排序前后 结果一致
 *
 *      多线程，重排序 可能出现问题
 */

/**
 * 原子性 ： 不可再分割
 *  JVM 设计时，按32bit 为 最小操作单位（大于32bit，不具备原子性，可能被拆分成多个 指令，线性不安全）
 *      byte：1 字节 --8 bit
 *      char：2 字节 --16 bit
 *      float：4 字节 --32 bit
 *      double：8 字节 --64 bit
 *      short：2 字节 --16 bit
 *      int：4 字节 --32 bit
 *      long：8 字节 --64 bit
 *      bollean :小于 32 bit
 *
 *      long、duoble 不具备原子性
 */

/**
 * 启动两个线程，同时操作一个变量 v = 0
 * 一个线程对该变量执行 N 次 v++
 * 另一个线程对该变量执行 N 次 v--
 * 问，当两个线程都执行结束时，v 的值是多少？
 * 期望值是多少？      0
 * 实际值是多少？      随机值(N 的值比较小时，出现随机值的情况较少，N 大时，出现情况较多）
 */

public class ThreadUnsafeDemo {
    private static final int N = 10_0000_0000;
    private static int v = 0; // 静态属性，所以共享

    // 因为 Add/Sub 线程都会修改 v
    // 所以才会产生线程安全问题，需要想办法解决

    static class Add extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                v++;
            }
        }
    }

    static class Sub extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                v--;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Add a = new Add();
        Sub s = new Sub();
        a.start();
        s.start();

        a.join();
        s.join();
        // 这里时，a 和 s 都结束了
        System.out.println(v);
    }
}
