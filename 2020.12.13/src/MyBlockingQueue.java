/**
 * @program: 2020.12.13
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-11 17:10
 **/

/**
 * 阻塞队列
 *  1.是一个队列(有放的，有取的，取的时候并不要求一定按照放的顺序取)
 *    FIFO /优先级队列(数据结构 --- 堆)
 *  2.放的线程，如果当前队列是满的，则阻塞，直到队列中有空间放
 *  3.取的线程，如果当前队列是空的，则阻塞，直到队列中有数据取
 */

/**
 * wait 一般放在 while 中
 */

//基于数组 循环队列 写 阻塞队列
public class MyBlockingQueue {
    private int[] array = new int[2];
    private volatile int size = 0;
    private int frontIndex = 0;
    private int rearIndex = 0;

    public synchronized void push(int element) throws InterruptedException {
        //用循环判断，每次被唤醒，都判断一次，避免被自己的 notify 唤醒
        while (size >= array.length) {
            wait();
        }
        array[rearIndex] = element;
        size++;
        rearIndex = (rearIndex + 1) % array.length;
        //notify();//可能因自己 唤醒了其他线程的 push ，导致wait 数，大于notify，所以全部唤醒
        notifyAll();
    }

    public synchronized int pop() throws InterruptedException {
        //用循环判断，每次被唤醒，都判断一次，避免被自己的 notify 唤醒
        while (size <= 0) {
            wait();
        }
        int element = array[frontIndex];
        frontIndex = (frontIndex + 1) % array.length;
        size--;
        notifyAll();
        return element;
    }

    public int size() {
        return size;
    }
}
