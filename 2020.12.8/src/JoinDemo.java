/**
 * @program: 2020.12.8
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-07 17:09
 **/

/**
 * 等待停止 -- b.join()
 *      A线程（这里是 main（主线程）） 等 B线程结束后，再继续
 *      B线程结束前，A线程会一直阻塞
 */

public class JoinDemo {

    static class B extends Thread {

        public void run() {
            long r = 0;
            for (int i = 0; i < 20_0000_0000L; i++) {
                r += i;
            }
            System.out.println("B 完了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        B b = new B();
        b.start();

        System.out.println("main 停止，B 结束后，main 继续");
        b.join();
        System.out.println("B 结束了，main 继续");
    }
}
