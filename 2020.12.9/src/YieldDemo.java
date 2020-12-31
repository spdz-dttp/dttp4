/**
 * @program: 2020.12.9
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-07 19:40
 **/

/**
 * Thread.yield()
 *  主动停止，仍是 就绪状态
 */
public class YieldDemo {

    static class A extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("A");
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("B");
                Thread.yield();// 当轮到 B 线程时，打印了一个B后，主动停止，CPU 重新分配资源
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.start();
        b.start();
    }
}
