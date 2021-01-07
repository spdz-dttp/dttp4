import java.util.Scanner;

/**
 * @program: 2020.12.13
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-11 18:33
 **/

//5 个生产者，一个消费者
public class MyBlockingQueueMain2 {

    private static final MyBlockingQueue queue = new MyBlockingQueue();

    static class Producer extends Thread {
        Producer(int i) {
            super("生产者-" + i);
        }
        @Override
        public void run() {
            for (int i = 0; true ; i++) {
                try {
                    queue.push(i);
                    System.out.println(getName() + ":放入：" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Producer(i).start();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
//            System.out.println("进入消费");
//            scanner.nextLine();
            int i = queue.pop();
            System.out.println("消费者取出了：" + i);
        }
    }
}
