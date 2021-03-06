/**
 * @program: 2020.12.4
 * @description: 多线程
 * @author: spdz
 * @create: 2020-12-04 16:02
 **/

/**
 * 线程：
 *  线程 和 进程 的关系  -- OS 中的线程
 *      进程 是 资源分配（操作系统进行资源协调分配） 的最小单位（除了CPU）
 *      线程 是 调度（进程的调度 -- CPU 的切换） 的最小单位（是CPU 资源分配的最小单位）
 *
 *  每个 线程 一定属于 一个唯一的 进程，一个 进程 下可以有 多个 线程（也可以只有 一个线程）
 *
 *  为什么这么设计
 *      A 做一件事，B 做一件事，A B需要配合
 *          若 A 和 B 都是 进程
 *                  由于 进程 是 资源分配的 的最小单位，所以A，B之间毫无关联，现在
 *              需要A 和 B 进行配合，需要传递一些信息，只能选择 成本较大 的 进程
 *              通信方式 进行信息传递
 *          若 A 和 B 都是 线程
 *              由于 A 和 B 同属 一个 进程下，可以共享很多资源，所欲可以直接共享信息
 */

/**
 * 进程调度
 *  并发（假同时）：
 *      多个进程在一个CPU下采用 时间片轮转 的方式，在一段时间之内，
 *      让多个进程都得以推进，称之为并发。
 *  并行（真同时）：多个进程在 多个CPU （多核）下分别 同时进行运行，这称之为并行。
 */

/**
 * java 中，由于 JVM 的限制，并发 很难做到 多进程模型，所以都是 多线程模型
 * （java 中只提 多线程，
 *      很少提 多进程，java 中的 线程 和 OS 原生线程 不是一回事，但可以参考理解）
 */

/**
 * 操作系统 最小 得分配 一个进程，这个进程可以是 单线程/多线程，调度 最小 得分配一个 线程。
 *
 *  操作系统给 每个线程 一个号，当轮到 这个线程时，会分配给 这个线程 一个进程
 *
 *  例：银行一个收银台 是一个 CPU（多个收银台是 多核），
 *      有些人在进行填表、复印文件等工作（还没准备好，阻塞状态），
 *      有些人们排队取钱（就绪 进程），
 *      排队的人中有的是 一个人来（单线程），
 *      有的是 一家人来（多线程，这些线程之间是 资源共享的，同属于一个 进程），
 *      都在排队，一家人（多线程）先去取完钱（进程）的概率大
 *      例：设一个人取钱需要一秒，
 *          三个人是一家人，还有两个单人，都在排队，共5人排队，一家人（多线程）取完钱 最慢要 5秒；
 *          三个单人排队，一个单人（单线程）完成相同的工作
 *          （取三次钱，因为其资源不共享，所以需要第一个整完，再整之后的，这里要排三次队），最慢要 9秒
 *
 *  单核情况下，使用 多线程，可能提高 速度
 */

/**
 *Java中 的并发编程 --- 多线程 编程
 *  1.很多情况下，可以 提升执行速度
 */

public class Multithreading {

}
