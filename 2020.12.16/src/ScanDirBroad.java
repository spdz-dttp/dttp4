import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: 2020.12.16
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-14 20:11
 **/

//广度优先 遍历 文件
public class ScanDirBroad {
    public static void main(String[] args) {
        File file = new File("测试目录\\非空");
        Queue<File> queue = new LinkedList<>();
        queue.offer(file);
        while (!queue.isEmpty()) {
            File node = queue.poll();
            if (node.isDirectory()) {
                System.out.println(node.getAbsolutePath() + "\\");
                File[] files = node.listFiles();
                for (File child :files) {
                    queue.offer(child);
                }
            }else {
                System.out.println(node.getAbsolutePath());
            }

        }
    }
}
