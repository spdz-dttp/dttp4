import java.util.concurrent.TimeUnit;

/**
 * @program: 2020.12.7
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-07 17:04
 **/

//TimeUnit

public class SleepDemo {
    public static void main(String[] args) {
        System.out.println(1);
        try {
            Thread.sleep(1);//1毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(2);
        try {
            TimeUnit.SECONDS.sleep(1);//1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(3);
        try {
            TimeUnit.MINUTES.sleep(1);//1分钟
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(4);
    }
}
