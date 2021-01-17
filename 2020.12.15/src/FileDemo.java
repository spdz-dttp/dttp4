/**
 * @program: 2020.12.15
 * @description: IO-1
 * @author: spdz
 * @create: 2020-12-13 19:12
 **/

import java.io.File;
import java.io.IOException;

/**
 * 1.文件是 硬盘上 一组数据的抽象概念
 * 2.文件分为“文件夹”和“普通文件”
 * 3.一个文件主要有两组数据组成:
 *  1.内容数据
 *  2.元数据(管理数据)
 *      1.文件名
 *      2.路径
 *      3.其他
 * 4.硬盘上的文件被“树形结构”组成起来.
 *  1. Windows 上，C: D: E: F: 都是树的根
 * 5.路径分为
 *  1.绝对路径: 从根开始的路径
 *  2.相对路径: 从“我”所在的位置开始的路径
 *      点’.’代表的 是本身
 *      点点'..’代表上级
 * 6.有路径不代表有文件
 */

/**
 * 创建新文件(元数据):
 *  普通文件: .createNewFile
 *      1. 可以成功创建:  创建并返回true
 *      2. 文件已存在: 直接返回false
 *      3. 中间路径不存在 创建失败: 抛出I0Exception .
 *  目录（文件夹）: .mkdir()
 *      1.可以成功创建: 创建并返回true
 *      2.文件已存在/创建失败: 直接返回false
 *      .mkdirs ()
 *          连同中间不存在的目录，一并创建 出来,只能创建目录。
 */

public class FileDemo {
    public static void main(String[] args) {

        // 使用绝对路径
        // 1. 对应一个实际存在的文件
        // 2. 对应一个实际不存在的文件
        // 3. 修改下文件的属性，观察下代码的打印有什么不同
        //String path = "E:\\javacode\\2020.12.15\\测试目录\\a.txt";

        //相对路径
        //此时的绝对路径 是相对于在哪运行的，
        //这里之所以是 E:\javacode\2020.12.15\FileDemo 路径
        //是因为，IDEA 运行时配置了默认路径，这里默认是 E:\javacode\2020.12.15
        //String path = "随机.txt";

        String path = "测试目录\\hello.txt";

        // 构建文件对象
        File file = new File(path);     // 路径对应的文件，但文件可能实际上不存在

        // 常见属性

//        //文件存在 && 是普通文件
//        System.out.println(file.isFile());
//        //文件存在 && 是文件夹
//        System.out.println(file.isDirectory());
//        //是绝对路径
//        System.out.println(file.isAbsolute());
//        //是隐藏文件
//        System.out.println(file.isHidden());
        //是否存在
        System.out.println(file.exists());
        //获得绝对路径
        System.out.println(file.getAbsolutePath());
//        //获得创建路径
//        System.out.println(file.getPath());
//        //名字
//        System.out.println(file.getName());
//        //上级目录
//        System.out.println(file.getParent());
//        //是否可读
//        System.out.println(file.canRead());
//        //是否可写
//        System.out.println(file.canWrite());
//        //是否可执行
//        System.out.println(file.canExecute());

        //创建普通文件
        try {
            boolean newFile = file.createNewFile();
            //当 上级目录（这里是 测试目录2）不存在时，创造普通文件失败
            //String path = "测试目录2\\hello.txt";//IOException
            System.out.println(newFile);
        }catch (IOException e) {
            e.printStackTrace();
        }

        //创建文件夹
        String path1 = "测试目录2";
        File file1 = new File(path1);
        boolean mkdir = file1.mkdir();
        System.out.println(mkdir);

        //连同中间不存在的文件夹一起创建
        String path2 = "a\\b\\c";
        File file2 = new File(path2);
        boolean mkdirs = file2.mkdirs();
        System.out.println(mkdirs);
    }

}
