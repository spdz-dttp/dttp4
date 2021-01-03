/**
 * @program: 2020.12.12
 * @description: 多线程9
 * @author: spdz
 * @create: 2020-12-09 22:42
 **/

/**
 * 单例模式
 *  某些类的对象，整个过程中，只需要一份
 *
 *  两种思想
 *      1.一开始就实例化好 对象   -- 饿汉模式
 *          -- 天生是 线程安全的（只在编译时 加载了一次，多线程没法修改）
 *      2.延迟实例化--按需加载   --懒汉模式  -- 不是天生线程安全的
 */

public class Main {

    static class SubThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                {
                    SingletonHungry instance = SingletonHungry.getInstance();
                    System.out.println(instance);
                }
                // 在多线程环境下，也能保证只会构造出唯一的对象
                // 没有线程安全问题

                {
                    SingletonLazy instance = SingletonLazy.getInstance();
                    System.out.println(instance);
                }

            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        for (int i = 0; i < 1000; i++) {
            new SubThread().start();
        }

        // 由于 SingletonHungry 构造方法是 private 的
        // 所以，会有语法错误
        // SingletonHungry mistake = new SingletonHungry();//error

        //如果要用到该类的对象
        SingletonHungry right = SingletonHungry.getInstance();

        SingletonLazy rightLazy = SingletonLazy.getInstance();

        // 小人途径，走后门构造一个新对象出来（强行构造出一个对象）
        // 利用反射可以构造
        // 了解即可
        SingletonHungry singletonHungry = SingletonHungry.class.newInstance();
    }
}
