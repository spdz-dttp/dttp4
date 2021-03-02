import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @program: 2020.12.31
 * @description: ${description}
 * @author: spdz
 * @create: 2021-01-01 18:28
 **/

public class HTTPEncodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("C++","utf-8");
        System.out.println(encode);

        String 我 = URLEncoder.encode("我", "utf-8");
        System.out.println(我);

        String decode = URLDecoder.decode("C%2B%2B","utf-8");
        System.out.println(decode);
    }
}
