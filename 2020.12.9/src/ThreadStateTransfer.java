import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @program: 2020.12.9
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-07 18:23
 **/

// TimeUnit  --> 2020.12.7

// 线程 状态变化
public class ThreadStateTransfer {

    static class SubThread extends Thread {

        public void run() {
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            //输入后，休眠 5秒
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            for (int i = 0; i < 50; i++) {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(100);//100 毫秒
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }// 理论上 是 RUNNABLE 和 TIMED_WAITING 交替出现，但实际只能看到 TIMED_WAITING
               // 因为 RUNNABLE 时间太短，出现概率太小

            System.out.println("子线程结束了");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread p = new SubThread();
        System.out.println(p.getState());// NEW
        p.start();
        System.out.println(p.getState());// RUNNABLE

        while (p.isAlive()) {
            System.out.println(p.getState());// 输入前 RUNNABLE，输入后 5秒 TIMED_WAITING
            TimeUnit.SECONDS.sleep(1);

        }

        System.out.println(p.getState());// TERMINATED
    }
}
