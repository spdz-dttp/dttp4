/**
 * @program: 2020.12.14
 * @description: 多线程11
 * @author: spdz
 * @create: 2020-12-13 16:56
 **/


/**
 * 线程池
 * 1.先检查线程池的 正式线程数量 是否已达上限
 *   只要没达到上限，创建一个正式线程处理任务
 * 2.当正式线程达到上限时(并不急于创建 临时线程)
 *   优先把任务放入任务队列中
 * 3.当任务队列已满
 *   检查 临时线程数量 是否已达上限
 *   只要没达上限，就创建 临时线程 处理任务
 *   （内部根据 空闲存活时间，销毁 临时线程）
 * 4.如果 临时线程 也达上限，线程池达上限
 *   执行指定的拒绝策略
 */

import java.util.concurrent.*;

/**
 * 拒绝策略
 *  1.new ThreadPoolExecutor.AbortPolicy()  //默认
 *  2.new ThreadPoolExecutor.CallerRunsPolicy()  //哪个线程调用，哪个线程执行
 *  3.new ThreadPoolExecutor.DiscardOldestPolicy()  //丢掉队列中最老的任务
 *  4.new ThreadPoolExecutor.DiscardPolicy()  //丢掉现在的任务
 */

public class JavaThreadPoolMain {

    //创建线程
    static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "名字");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //阻塞队列
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);

        // 创建线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                10,//正式线程
                10,//正式线程 + 临时线程
                0,//临时线程 空闲 存活时间
                TimeUnit.SECONDS,//存活时间单位
                queue,//阻塞队列
                new MyThreadFactory(),//线程工厂：创建线程
                new ThreadPoolExecutor.CallerRunsPolicy()//拒绝策略
                //主线程调用，主线程执行（这里任务是休眠 1分钟）
        );

        for (int i = 0; true; i++) {
            // 创建让线程池执行的任务
            Runnable target = new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            // 等同于把任务提交给线程池
            // 线程池内部会选择一个空闲的线程去执行该任务
            System.out.println("提交第 " + i + " 个任务");
            System.out.println(queue.size());
            threadPool.execute(target);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
