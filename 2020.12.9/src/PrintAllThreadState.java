/**
 * @program: 2020.12.9
 * @description: 多线程6--线程的状态
 * @author: spdz
 * @create: 2020-12-07 17:13
 **/


/**
 * 线程 的属性
 *     id: JVM内部，每个线程的唯一 标识.
 *     name: 线程的名字，为了打印日志、调试使用，可以重复
 *     优先级: 变相地影响到线程被调度到的机会( 建议型的属性,不是一定 按照优先级走)
 *     状态: 类似 进程调度时 的进程状态
 *     是否存活: 一个线程的状态 不是 NEW 或 TERMINATED，则 存活
 *     是否是后台线程: 什么情况下JVM会退出
 *                      1.调用了 System.exit()
 *                      2.所有 非后台线程 退出，JVM 就可以退出了
 */

/**
 * 线程的状态
 *  JDK中通过枚举来描述
 *      NEW ： 创建
 *      RUNNABLE ： 就绪和运行
 *
 *    这三个都是 阻塞（阻塞状态 放弃 CPU ，条件满足后，才会变为 就绪状态）
 *      BLOCKED
 *          synchronized 机制属于 BLOCKED 状态
 *      WAITING
 *          wait/notify 机制属于 WAITING
 *      TIMED_WAITING
 *          sleep 属于属于 TIMED_WAITING
 *
 *      TERMINATED ：终结
 */

public class PrintAllThreadState {

    public static void main(String[] args) {
        Thread.State[] values = Thread.State.values();//通过 枚举描述 （Thread.State）
        for (Thread.State e :values) {
            System.out.println(e);
        }
    }
}
