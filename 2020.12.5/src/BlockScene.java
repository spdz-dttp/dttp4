import java.util.Scanner;

/**
 * @program: 2020.12.5
 * @description:
 * @author: spdz
 * @create: 2020-12-05 18:34
 **/

//在设计编程模型时，有些场景下，会发生阻塞，如果同时还想做其他事情，只能创建其他线程完成

/**
 * 计算斐波那契数列的值(计算时间较长)
 * （主线程负责通过 scanner读取用户输入
 *  专门启动其他线程，来负责计算）
 */

public class BlockScene {

    // 时间复杂度是 O(2 ^ n)
    // 非常大的时间复杂度
    private static long fibonacci(int i) {
        if (i <= 2) {
            return 1;
        }
        return fibonacci(i-1) + fibonacci(i-2);
    }

    private static class FibThread extends Thread {
        private final int n;

        FibThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            System.out.printf("fib(%d) = %d%n", n, fibonacci(n));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 主线程只负责接待客人
            int n = scanner.nextInt();
            //System.out.printf("fib(%d) = %d%n", n, fib(n));
            new FibThread(n).start();   // 每次计算交给一个新人去处理
        }
    }


}
