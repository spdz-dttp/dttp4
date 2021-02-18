import java.io.*;

/**
 * @program: 2020.12.17
 * @description: IO-3
 * @author: spdz
 * @create: 2020-12-16 15:30
 **/

// IO流

/**
 * 读写方式
 *  1.单字节读写
 *  2.批量方式读写
 *
 *  建议使用批量操作
 *      如果 JVM 没有 缓冲区，批量操作 也可以提升速度
 *      因为 内存读写速度 比 硬盘读写速度 快得多
 */

public class FileStreamDemo {

    public static void main1(String[] args) {
//        File file = new File("测试目录");
//        boolean mkdir = file.mkdir();
//        System.out.println(mkdir);
//        File file1 = new File("测试目录\\a.txt");
//        try {
//            boolean newFile = file1.createNewFile();
//            System.out.println(newFile);
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

        //inputDemo();
        outputDemo();
    }


    /**
     * 测试目录\\a.txt 中的内容是 "Hello\r\nWorld"
     *  \r  CR  0x13  光标回行首
     *  \n  LN  0x10  换行
     *
     *  换行 在不同操作系统的区别
     *  Windows： \r\n
     *  Linux:  \n
     *  Mac:  \r
     */
    private static void inputDemo() {
        //单字节读
//        try {
//            InputStream is = new FileInputStream("测试目录\\a.txt");
//            int count = 0;
//            while (is.read() != -1) {
//                count++;
//            }
//            System.out.println(count);
//            is.close();//要关闭
//        }catch (IOException e) {
//            e.printStackTrace();
//        }


//        // try-with-resource 形式
//        // FileInputStream 就是 InputStream 的一个子类
//        // InputStream 是一种 输入 字节 流
//        // FileInputStream 是一种 输入 字节（来自文件） 流
//        try(InputStream is = new FileInputStream("测试目录\\a.txt")) {
//            int count = 0;
//            while (true) {
//                // 返回数据流中的下一个字节
//                // 返回 int，因为返回值需要返回 -1，这个和真正的数据做区分
//                // 返回 -1（EOS），读到文件结尾了
//                // -1 被称为 EOS（End Of Stream）
//                //内存读写速度 比 硬盘读写速度 快得多
//                int b = is.read();//一个一个读，没有缓冲区，需要多次 硬盘读写，速度慢
//                if (b == -1) {
//                    break;
//                }
//                count++;
//                System.out.printf("%d%n",b);//十进制
//                System.out.printf("%02x%n",b);//十六进制
//                System.out.printf("%c%n",b);//字符
//                System.out.println("===============");
//            }
//            System.out.println(count);
//        }catch (IOException e ) {
//            e.printStackTrace();
//        }

        //批量读
        try(InputStream is = new FileInputStream("测试目录\\a.txt")) {
            byte[] buffer = new byte[8];//缓冲区
            int count = 0;// read 的调用次数
            while (true) {
                int n = is.read(buffer);//将数据流中的字节读入缓冲区（建议使用）
                // n 代表 最终读到的字节个数
                //例：一个数据流 12个字节，第一次读到8个，第二次4个，第三次返回-1，代表读完了
                //多进行 内存读写，减少 硬盘读写，提高速度
                //is.read(buffer, 0, buffer.length);
                System.out.println("n = " + n);
                count++;
                if (n == -1) {
                    System.out.println("read() 一共被调用" + count + "次");
                    break;
                }
                for (int i = 0; i < n; i++) {
                    int b = buffer[i];

                    System.out.printf("%d%n",b);//十进制
                    System.out.printf("%02x%n",b);//十六进制
                    System.out.printf("%c%n",b);//字符
                    System.out.println("===============");
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void outputDemo() {
        // 1. 如果文件不存在，会进行文件的创建（失败的条件等同于平时创建文件失败的条件）
        // 2. 如果文件存在，会进行“覆盖”方式的写入
        try(OutputStream os = new FileOutputStream("测试目录\\b.txt")) {
            //单字节写入
//            os.write('H');
//            os.write('\r');
//            os.write('\n');
//            os.write('W');

            //批量方式写入
            byte[] buffer = new byte[8];
            buffer[0] = 'H';
            buffer[1] = '\r';
            buffer[2] = '\n';
            buffer[3] = 'A';
            os.write(buffer,0,4);

            // 无论是哪种方式写入，一定需要做 flush() 操作(泄洪操作)
            os.flush();// 强制要求把系统（软件部分 JVM/OS) 中缓冲的数据，刷新到真正的硬盘中
                       // 为了提升速度，很多Output的类实现中，都会包含缓冲区
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * utf-8 变长的字符集---每个真正的 char对应的字节, 长度不确定（1 - 3）,
     *      支持char范围很广(全世界的文字)
     * gbk 定长的字符集---每个真正的 char 对应的字节, 长度一定是2，
     *      仅支持字母、数字、几个特殊符号+中文
     */
    public static void main2(String[] args) {
        // 带有中文字符的读写
        try(InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            byte[] buffer = new byte[1024];
            int n = 0;
            while ((n = is.read(buffer)) != -1) {
//                for (int i = 0; i < n; i++) {
//                    System.out.printf("|%d|%02x|%n",buffer[i],buffer[i]);
//                }//输出的是中文所表示 的 6个 字节所对应的 ASCLL（中文 一般是 3个字节 共同表示）

                //这个方法不好
                //假设 已知 buffer 读取的中文，且没有出现被拆断的形式（中文 一般是 3个字节 共同表示）
                String str = new String(buffer,0,n,"UTF-8");
                System.out.println(str);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    //将 字节流 转换成 字符流，再输出
    public static void main(String[] args) throws IOException {
        // 1. 先有字节流
        try(InputStream is = new FileInputStream("测试目录\\中文.txt")) {
            // 2. 利用字节流,构造出 字符流
            try(Reader reader = new InputStreamReader(is,"UTF-8")) {
                // 读取的单位变成了 字符 char

//                //单字符读取
//                {
//                    int n = 0;
//                    while ((n = reader.read()) != -1) {
//                        System.out.printf("%c%n",n);
//                    }
//                }

                //批量读
                {
                    char[] buffer = new char[1024];
                    int n = 0;
                    while ((n = reader.read(buffer)) != -1) {
                        for (int i = 0; i < n; i++) {
                            System.out.print(buffer[i]);
                        }
                    }
                }
            }
        }

//        // 字符集默认按照项目的字符集编码来（utf-8）,不好更改
//        try(Reader reader = new FileReader("测试目录\\中文.txt")) {
//            char[] buffer = new char[1024];
//            int n;
//            while ((n = reader.read(buffer)) != -1) {
//                for (int i = 0; i < n; i++) {
//                    System.out.print(buffer[i]);
//                }
//            }
//        }
    }
}
