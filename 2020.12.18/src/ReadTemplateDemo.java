import java.io.*;
import java.util.Scanner;

/**
 * @program: 2020.12.18
 * @description: IO-4
 * @author: spdz
 * @create: 2020-12-16 19:53
 **/

/**
 * 关于Windows 和 Linux 的一些重要的区别:
 *  1. Windows 的文件系统是多棵树，Linux 是一棵树
 *  2. Windows 的路径分割符是'\’，Linux 是'/'
 *  3. Windows 的换行符是’\r\n’, Linux是'\n’
 *  4.大部分情况下，Windows 的默认字符集是GB18030 , Linux是UTF-8
 */

//按行读
public class ReadTemplateDemo {

    public static void main(String[] args) throws IOException {

//        File file = new File("测试目录");
//        boolean mkdir = file.mkdir();
//        File file1 = new File("测试目录\\中文.txt");
//        try {
//            boolean newFile = file1.createNewFile();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

        字符数据1();
        字符数据2();
    }

    public static void 字符数据1() throws IOException {
        try(InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            try(Reader reader = new InputStreamReader(is,"UTF-8")) {
                try(Scanner scanner = new Scanner(reader)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        System.out.println(line);
                    }
                }
            }
        }
    }

    public static void 字符数据2() throws IOException {
        try(InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            try(Scanner scanner = new Scanner(is,"UTF-8")) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            }
        }
    }

}
