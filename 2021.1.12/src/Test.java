/**
 * @program: 2021.1.12
 * @description: ${description}
 * @author: spdz
 * @create: 2021-01-12 15:45
 **/
public class Test {

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.addFirst1(1);
        demo.addFirst1(2);
        demo.addFirst1(3);
        demo.display();
        demo.display2(demo.reverseLists());
    }
}
