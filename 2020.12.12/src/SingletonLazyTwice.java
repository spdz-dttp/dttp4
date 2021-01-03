/**
 * @program: 2020.12.12
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-10 16:22
 **/

//懒汉模式单例
public class SingletonLazyTwice {

    private final String name;
    private final int age;
    private final String gender;

    private static SingletonLazyTwice instance1 = null;

    //被 volatile 修饰后，实例化对象时，不能 代码重排
    private static volatile SingletonLazyTwice instance = null;


    // 不是 线程安全的
    // 这把锁加的毫无意义
    public static SingletonLazyTwice getInstanceUnsafe() {
        if (instance1 == null) {
            synchronized (SingletonLazyTwice.class) {
                instance1 = new SingletonLazyTwice();
            }
        }

        return instance1;
    }

    // 通过二次判断，既保证了线程安全，又减少了抢锁的次数，成本低
    // 这个版本中仍然存在着一个小错误
    public static SingletonLazyTwice getInstanceWrong() {
        if (instance1 == null) {
            synchronized (SingletonLazyTwice.class) {
                // instance1 可能是 null，也可能不是 null
                if (instance1 == null) {
                    // 这里因为锁的存在，只会执行一次
                    // 保证了单例
                    instance1 = new SingletonLazyTwice();//代码可能会被重排序
                }
                // 抢锁之前，instance1 是 null
                // 但是从抢锁到抢锁成功这段期间
                // instance1 已经不是 null
                // 代表已经被之前抢到锁的线程实例化好了
                // 也就什么都不需要做了

            }
        }

        return instance1;
    }

    public static SingletonLazyTwice getInstance() {
        if (instance == null) {
            // instance == null，然后抢锁
            // 如果 instance != null，不需要抢锁
            // 一个线程持有锁，不能干扰 另一个 不抢锁的线程
            synchronized (SingletonLazyTwice.class) {
                if (instance == null) {
                    instance = new SingletonLazyTwice();
                }
            }
        }

        return instance;
    }

    private SingletonLazyTwice() {
        name = "hhh";
        age = 34;
        gender = "男";
    }
}
