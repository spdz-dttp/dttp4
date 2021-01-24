import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @program: 2020.12.16
 * @description: IO-2
 * @author: spdz
 * @create: 2020-12-14 16:38
 **/

/**
 * 文件(元数据)的重命名  .renameTo()
 *  1.文件的重命名
 *  2.剪切 + 粘贴
 *
 */

/**
 * 文件(元数据)的删除  .delete()
 *  1.普通文件的删除
 *  2.目录（文件夹）的删除
 *      必须是一个空目录
 *
 *  .deleteOnExit() 删除
 *      该文件不是立即删除，而是等 JVM 退出 再删除
 *      用于 运行期间需要，退出后不需要 的文件
 *
 *  只允许删除叶子结点
 *
 *  这里的删除是彻底删除，不是进入回收站
 *
 *  文件的删除都是对 硬盘上数据的处理，根本不是 内存上的数据
 */

/**
 * 文件(元数据)的遍历
 *  只能遍历该文件的下一级
 *
 *  .listFiles() 返回一个 File[]
 *  .list() 返回一个 String[]
 *
 *  1.非空目录  返回 长度不是 0 的数组
 *  2.空目录  返回 长度是 0 的数组
 *  3.普通文件  返回 null
 */

public class FileDemo {
    public static void main(String[] args) {
//        String path = "测试目录";
//        File file = new File(path);
//        boolean mkdir = file.mkdir();
//        System.out.println(mkdir);
//        File file1 = new File("测试目录\\a.txt");
//        try {
//            boolean newFile = file1.createNewFile();
//            System.out.println(newFile);
//        }catch(IOException e) {
//            e.printStackTrace();
//        }

//        File file = new File("测试目录\\a.txt");

//        //模拟文件重命名操作
//        File dest = new File("测试目录\\b.txt");
//        boolean b = file.renameTo(dest);
//        System.out.println(b);

//        //模拟剪切-粘贴
//        File dest = new File("a.txt");
//        boolean b = file.renameTo(dest);
//        System.out.println(b);

//        File dest = new File("a.txt");
//        boolean b = file.renameTo(dest);
//        System.out.println(b);

//        //文件的删除
//        File file = new File("测试目录\\非空");
//        boolean delete = file.delete();
//        System.out.println(delete);//false


//        Scanner scanner = new Scanner(System.in);
//        {
//            File file = new File("测试目录\\b.txt");
//            file.delete();
//            System.out.println("等待一会，先观察 b.txt 文件是否已经被删除！");
//            scanner.nextLine();
//        }
//
//        {
//            File file = new File("测试目录\\c.txt");
//            file.deleteOnExit();
//            System.out.println("等待一会，先观察 c.txt 文件是否已经被删除！");
//            scanner.nextLine();
//        }

        File file = new File("测试目录\\非空");

//        File[] files = file.listFiles();
//        for (File f: files) {
//            System.out.println(f.getAbsolutePath());
//        }


        String[] list = file.list();
        System.out.println(list);
        System.out.println(list.length);
        for (String s: list) {
            System.out.println(s);
        }

    }
}
