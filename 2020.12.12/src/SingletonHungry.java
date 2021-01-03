/**
 * @program: 2020.12.12
 * @description:
 * @author: spdz
 * @create: 2020-12-10 15:24
 **/

//饿汉模式的单例
// 通过 final 修饰类，避免有人定义该类的子类来构造对象（有时）
public final class  SingletonHungry{
    // 1. 我需要保存我仅有的一个对象的引用
    // 了解：final 修饰，保证了原子性了
    //被final 修饰，只能初始化 一次，不能修改
    private static final SingletonHungry instance;

    // 2. 饿汉模式，也就是一开始就初始化
    //    静态代码块 or 直接初始化都可以
    static {
        // 只发生在 SingletonStarve 被加载时执行
        // 而 SingletonStarve 的加载，只发生一次
        instance = new SingletonHungry();
    }

    // 3. 如果需要该类的对象，只能使用这个 instance 指向的对象
    //    所以，需要提供方法，把对方返回
    public static SingletonHungry getInstance() {
        // 这个方法 不管 被调用了几次，返回的 都是指向同一个对象的引用
        return instance;
    }

    // 4. 尽可能的避免有人无意中构造出该类的对象
    //    所以，把类的构造方法标记有 private，减少误用可能
    private SingletonHungry() {
    }
}