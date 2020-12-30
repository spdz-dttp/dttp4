/**
 * @program: 2020.12.8
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-07 17:08
 **/

import java.util.Scanner;

/**
 * 如何不使用 Thread 中设计的方式通知线程停止
 */
public class AboutThreadStop2 {
    // 定义了一个类变量，线程之间可以共享
    // volatile 回头讲
    // B 线程可以通过观察该变量，知道是否有人让它停止
    private static volatile boolean isInterrupted = false;//设置 中断标志 为 false

    static class B extends Thread {
        B() {
            super("B");
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("冲");
                    Thread.sleep(50000);
                    System.out.println("是否有人让我停止: " + isInterrupted);
                    if (isInterrupted) {
                        break;
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        B b = new B();
        b.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("准备通知停止");
        isInterrupted = true;
        System.out.println("已经通知停止");
    }
}
