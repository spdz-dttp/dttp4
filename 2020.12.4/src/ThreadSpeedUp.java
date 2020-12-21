/**
 * @program: 2020.12.4
 * @description:演示多线程可以提升速度
 * @author: spdz
 * @create: 2020-12-04 23:03
 **/

/**
 * 计算 从 1 + N 的耗时
 */
public class ThreadSpeedUp {
    //Java中键入数字时，编译器会自动将其作为整数读取,
    //当你键入(long)100_0000_0000(不在整数范围内(-2147483648—2147483647))时，报错，
    //必须指定100_0000_0000L
    private static final long N = 10_0000_0000;
    private static final long C = 10;

    //单线程
    private static void 串行方式计算() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < C; i++) {
            long r = 0;
            for (int j = 0; j < N; j++) {
                r += j;
            }
            System.out.println(r);
        }

        long end = System.currentTimeMillis();
        System.out.printf("串行模式下，耗时：%.2f秒%n",(end - begin)/1000.0);
    }

    //线程的类
    private static class Calc extends Thread {
        @Override
        //要求该线程运行的任务
        public void run() {
            long r = 0;
            for (long i = 0; i < N; i++) {
                r += i;
            }
            System.out.println(r);
        }
    }

    //多线程
    //一共要计算 C 次
    private static void 并行方式计算() throws InterruptedException {
        long begin = System.currentTimeMillis();
        //一共启动 C 个线程

        //需要启动 C - 1 个线程
        //每个线程，只需计算 1 + ...N,就可以了
        Calc[] threads = new Calc[(int)C - 1];
        for (int i = 0; i < C - 1; i++) {
            threads[i] = new Calc();
            threads[i].start();
        }

        //主线程 ，自己再进行 一次
        long r = 0;
        for (long i = 0; i < N; i++) {
            r += i;
        }
        System.out.println(r);

        //必须等其他 C - 1 个线程执行结束，才算真正执行结束
        for (int i = 0; i < C - 1; i++) {
            threads[i].join();
        }

        long end = System.currentTimeMillis();
        System.out.printf("并行模式下，耗时：%.2f秒%n",(end - begin)/1000.0);

    }

    public static void main(String[] args) throws InterruptedException {
        //预热，防止影响速度
        //JVM 内部有 JIT，执行过程中，会进行代码优化
        //java 程序是 越跑越快的，运行一段时间后，才能达到理想值
        //为了 避免 开始启动阶段，影响测试效果，加一个预热
        for (int i = 0; i < 20; i++) {
            long r = 0;
            for (int j = 0; j < N; j++) {
                r += j;
            }
        }

        串行方式计算();
        并行方式计算();
    }
}
