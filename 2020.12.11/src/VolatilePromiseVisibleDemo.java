/**
 * @program: 2020.12.11
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-08 23:57
 **/

public class VolatilePromiseVisibleDemo {

    private static volatile Boolean f = false;

    //private static Boolean f = false;
    //不加 volatile ，子线程 因内存可见性 看不见 f 被主线程修改
    //只能看见自己 工作内存 中的 f = false

    static class A extends Thread {
        public void run(){
            while (!f) {

            }
            System.out.println("A结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new A();
        a.start();

        Thread.sleep(2000);

        System.out.println("休眠结束了");
        f = true;
        a.join();
        System.out.println("main 结束了");
    }
}
