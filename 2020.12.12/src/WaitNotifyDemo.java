import java.util.Scanner;

/**
 * @program: 2020.12.12
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-10 22:10
 **/

public class WaitNotifyDemo {
    private static WaitNotifyDemo p = new WaitNotifyDemo();

    static class A extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("等待前");

                synchronized (p) {
                    p.wait();
                }

                System.out.println("等待后");
            }catch(InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread a = new A();
        a.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        synchronized (p) {
            p.notifyAll();// p.notify()也可以
        }
    }

}
