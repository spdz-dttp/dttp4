import java.io.*;

/**
 * @program: 2020.12.18
 * @description: ${description}
 * @author: spdz
 * @create: 2020-12-16 23:59
 **/

//文件复制
public class FileCopy {

    public static void main(String[] args) throws IOException {
        String sourcePath = "测试目录\\中文2.txt";
        String destPath = "测试目录\\中文-复制.txt";

        try(InputStream is = new FileInputStream(sourcePath)) {
            try(OutputStream os = new FileOutputStream(destPath)) {
                byte[] buffer = new byte[8192];
                int n;
                while ((n = is.read(buffer)) != -1) {
                    os.write(buffer,0,n);
                }
                os.flush();
            }
        }
    }

}
