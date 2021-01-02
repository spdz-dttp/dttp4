/**
 * @program: 2020.12.11
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-08 22:28
 **/



public class FixUnsafe {

    private static final int N = 10_0000_0000;
    private static int v = 0;

    static class Add extends Thread {

        public void run() {

            //这里是 Add.class，下面也是Add.class
            //这里是 Sub.class，下面也是Sub.class
            //这里是 FixUnsafe.class，下面也是FixUnsafe.class
            synchronized (Add.class) {
                for (int i = 0; i < N; i++) {
                    v++;
                }
            }

        }
    }

    static class Sub extends Thread {

        public void run() {

            synchronized (Add.class) {
                for (int i = 0; i < N; i++) {
                    v--;
                }
            }

        }
     }

    public static void main(String[] args) throws InterruptedException {
        Thread add = new Add();
        Thread sub = new Sub();
        add.start();
        sub.start();
        add.join();
        sub.join();

        //期望值是  0
        System.out.println(v);
    }

}
