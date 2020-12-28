/**
 * @program: 2020.12.6
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-06 14:35
 **/

/**
 * 线程 执行 顺序 的随机性
 *  加入 就绪队列的时机 是确定的，但什么时候 被调度到CPU 不确定(随机)，
 *  什么时候 被从CPU上调度下来 不确定(随机)
 *
 * 但为什么 现象上 看起来顺序 是 固定的?
 *  主线程现在能创建A/B 两个线程，代表现在 主线程 占据着CPU ，
 *  任何代码执行的 前提，都是 该线程拥有CPU
 *  主线程（这里是 main() 方法）创建两个线程 + 主线程完成任务 需要的时间，远远小于 时间片，
 *  所以，可以认为时间片 耗尽之前，主线程 可以把所有事情都干完!
 *  只有 主线程 执行结束 放弃 CPU, A和B才有资格 抢CPU!
 *  大部分 0S 实现的时候 会考虑公平性 —— 先就绪的先被调度，
 *  所以，因为 a.start 先被执行，所以大概率，A线程 先拥有CPU，
 *  然后也是因为数据很少，所以就把事情都干完了 再轮到B
 *
 * 当数据量大时，顺序是 随机的
 *      如下代码
 */

public class WhoFirst {
    static class PrintA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("A");
            }
        }
    }

    static class PrintB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("B");
            }
        }
    }

    public static void main(String[] args) {
        PrintA a = new PrintA();
        PrintB b = new PrintB();
        a.start();
        b.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main");
        }
    }


}
