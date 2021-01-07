/**
 * @program: 2020.12.13
 * @description: 多线程10
 * @author: spdz
 * @create: 2020-12-11 16:06
 **/

/**
 * sleep 和 wait
 *  相同点：都会主动放弃 CPU ，并在一段时间内 放弃 调度 资格
 *  不同点：
 *      1.sleep 是线程休眠一段时间
 *        wait 是 等待 被其他线程唤醒，带一个超时时间
 *      2.sleep 是 static Thread 中的方法
 *        wait 是 Object（指任何类） 中的方法
 *      3.sleep 任何时候都能用
 *        wait 使用前 先加锁
 *      4.sleep 不释放锁
 *        wait 额外释放锁
 */

public class WaitReleaseLock {

    static final Object o1 = new Object();
    static final Object o2 = new Object();

    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                synchronized (o1) {
                    synchronized (o2) {
                        o2.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread subThread = new SubThread();
        subThread.start();

        // 主动放弃CPU，保证让子线程先运行起来
        Thread.sleep(1000);

        // 子线程已经运行起来了
        synchronized (o2) {
            System.out.println("o2 加锁成功");//因为wait 会额外 释放锁，所以 可以输出
        }

        synchronized (o1) {
            System.out.println("o1 加锁成功");
        }
    }
}
