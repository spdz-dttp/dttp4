/**
 * @program: 2020.12.11
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-08 18:48
 **/

/**
 * synchronized 关键字的语法
 */
public class SynchronizedSyntaxDemo {
    /**
     * 修饰 方法
     *  相当于 没有被修饰 的方法，内部加了 被修饰 的代码块
     */
    public synchronized void method() {

    }

    //和 method() 等价
    public void method1() {
        synchronized (this) {

        }
    }

    public static synchronized void staticMethod() {

    }

    //和 staticMethod() 等价
    public static void staticMethod1() {
        synchronized (SynchronizedSyntaxDemo.class) {

        }
    }

    /**
     * 修饰代码块
     */
    public void otherMethod() {
        // 括号里跟着指向对象的引用，引用不能是 null
        // 只要是引用就行，没有任何要求
        Object o = new Object();
        synchronized (o) {
        }

        synchronized (this) {
        }

        // 反射中，指向当前 类对象
        synchronized (SynchronizedSyntaxDemo.class) {
        }
    }
}
