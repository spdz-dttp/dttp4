import java.io.*;
import java.util.Scanner;

/**
 * @program: 2020.12.18
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-16 23:00
 **/

public class WriteTemplateDemo {

    public static void 字节数据() throws IOException {
        // buffer 中假设是有效数据
        byte[] buffer = new byte[8192];

        try(OutputStream os = new FileOutputStream("测试目录\\中文2.txt")) {
            for (int i = 0; i < 8; i++) {
                os.write(buffer,i * 1024,1024);
            }
            os.flush();
        }
    }

    public static void 字符数据() throws IOException{
        // buffer 中假设是有效数据
        //char[] buffer = new char[8192];
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        try(OutputStream os = new FileOutputStream("测试目录\\中文2.txt")) {
            try(Writer writer = new OutputStreamWriter(os,"UTF-8")) {
//                for (int i = 0; i < 8; i++) {
//                    writer.write(buffer,i * 1024,1024);
//                }
//                writer.flush();

                try(PrintWriter printWriter = new PrintWriter(writer)) {
                    printWriter.println(s);
                    //printWriter.printf("你好");

                    printWriter.flush();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        字符数据();
    }
}
