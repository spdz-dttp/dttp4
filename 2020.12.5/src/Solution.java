import java.util.ArrayList;
import java.util.List;

/**
 * @program: 2020.12.5
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-06 23:42
 **/
public class Solution {

    //杨辉三角
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        if (numRows < 1) {
            return ret;
        }
        List<Integer> one = new ArrayList<>();
        one.add(1);
        ret.add(one);
        for (int i = 1; i < numRows; i++) {
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            List<Integer> pre = new ArrayList<>();
            pre = ret.get(i-1);
            for (int j = 1; j < i; j++) {
                int x = pre.get(j);
                int y = pre.get(j-1);
                cur.add(x + y);
            }
            cur.add(1);
            ret.add(cur);
        }
        return ret;
    }

    public static void toString(List<List<Integer>> ret) {
        for (int i = 0; i < ret.size(); i++) {
            for (int j = 0; j < ret.size() - i - 1; j++) {
                System.out.print(" ");
            }
            for (int e :ret.get(i)) {
                System.out.print(e + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        toString(generate(5));

    }
}
