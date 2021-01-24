import java.io.File;

/**
 * @program: 2020.12.16
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-14 19:15
 **/

//深度优先 遍历 文件
public class ScanDirDepth {
    public static void main(String[] args) {
        File file = new File("测试目录\\非空");
        scanDir(0,file);

    }

    private static void scanDir(int n, File file) {
        for (int i = 0; i < n; i++) {
            System.out.print("    ");
        }
        System.out.println(file.getAbsolutePath() + "\\");

        File[] files = file.listFiles();

        // 隐含的递归停止条件：空文件夹（files 的长度 == 0） 的时候，不进入循环
        for (File child :files) {
            if (child.isDirectory()) {
                scanDir(n+1,child);
            }else {
                for (int i = 0; i < n+1; i++) {
                    System.out.print("    ");
                }
                System.out.println(child.getAbsolutePath());
            }
        }
    }

}
