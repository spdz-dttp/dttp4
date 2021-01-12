/**
 * @program: 2020.12.14
 * @description: 比较器练习
 * @author: spdz
 * @create: 2020-12-12 16:52
 **/

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 输入 学生人数
 *      降序(0)/升序(1)
 *      姓名 成绩
 *      姓名 成绩
 *      ......
 *
 * 输出 按 降序/升序 排序的 （姓名 成绩）,成绩相同按顺序输出
 */

public class ComparatorDemo {
    static class Student {
        String name;
        int sorce;
        int num;

        Student(String name,int sorce,int num) {
            this.name = name;
            this.sorce = sorce;
            this.num = num;
        }
    }

    static class AscComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            if (o1.sorce != o2.sorce) {
                return o1.sorce - o2.sorce;
            }else {
                return o1.num - o2.num;
            }
        }
    }

    static class DescComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            if (o1.sorce != o2.sorce) {
                return o2.sorce - o1.sorce;
            }else {
                return o1.num - o2.num;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int t = scanner.nextInt();
        Boolean order = true;
        if (t == 0) {
            order = false;
        }
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int sorce = scanner.nextInt();
            students[i] = new Student(name,sorce,i);
        }
        if (order) {
            Arrays.sort(students,new AscComparator());
        }else {
            Arrays.sort(students,new DescComparator());
        }
        for (Student e:students) {
            System.out.println(e.name + " " + e.sorce);
        }
    }

}
