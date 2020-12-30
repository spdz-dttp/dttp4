/**
 * @program: 2020.12.8
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-07 17:08
 **/

import java.util.Scanner;

/**
 * 停止一个线程
 *  主线程 通知 另一个线程停止
 *  另一个线程 收到通知后，自行决定 何时、以何种方式 停止
 */

/**
 * A（主线程） 调用
 *      t.interrupt();//通知 t 引用代表的线程，要停止了
 *
 * B（子线程）收到通知
 *  分情况：
 *      B 线程 是否正在 执行 类似 sleep 的操作
 *          没有：Thread.interrupt()/t.isInterrupted()
 *                返回 true/false 是否有人让你停止
 *          有：通过 InterruptedException 异常通知，通知的非常及时
 *              如果 不用异常 进行通知，会等到 sleep 结束后，才收到通知
 *
 *              sleep 状态下，线程是休眠的（停止的），线程不在 CPU 上
 *              但JVM 是醒着的，JVM 用异常进行通知
 * B 停止
 *      break/return 退出循环
 */

/**
 * t.interrupt();在父线程中 将 中断标致 改为 true；
 *
 * Thread.interrupt()/b.isInterrupted() //子线程 通过 这两个方法 看 中断标致
 *  b.isInterrupted() :
 *      返回 B 线程的 isInterrupted（是否中断）
 *      非 B 的线程 想看 是否有人 让 B 停止，只能看，不能动
 *  Thread.interrupted() :
 *      返回当前线程的 isInterrupted
 *      isInterrupted = false
 *      给 B 自己看，自己看完，会 把中断标志 清理为 false
 *
 * 如果是 直接异常通知，中断标志 会被改为 false
 *
 */

public class AboutThreadStop {

    static class B extends Thread {

        B() {
            super("B");
        }

        public void run() {
            Thread t = Thread.currentThread();
            while (true) {
//                System.out.println("芜湖，起飞！");
//                boolean interrupted2 = t.isInterrupted();
//                System.out.println("是否有人让我停止运行: " + interrupted2);
//                if (interrupted2 == true) {
//                    break;  // break、return 都可以
//                }

                try {
                    System.out.println("芜湖，起飞！");
                    Thread.sleep(1000);// 停1秒
//                    boolean interrupted2 = Thread.interrupted();
//                    System.out.println(interrupted2);//false
                    boolean interrupted = t.isInterrupted();
                    System.out.println("是否有人让我停止运行: " + interrupted);
                    if (interrupted == true) {

                        // 停止前还可以做些其他事

                        break;  // break、return 都可以
                    }
                }catch (InterruptedException e) {
                    System.out.println("有人让我停止运行，但因为我正在 sleep，所以我收到了这个异常");
                    // 结束后跳出循环了，所以 run 方法正常结束
                    return;
                }
            }

        }
    }

    public static void main(String[] args) {
        Thread t = new B();
        t.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();//输入任意数后，执行下一步
        System.out.println("准备通知 B 线程停止");

        t.interrupt();//通知 B 停止运行，isInterrupted（中断标志） 改为 true
        System.out.println("已经通知 B 线程停止");
    }
}
