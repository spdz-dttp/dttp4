import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: 2020.12.31
 * @description: 网络-HTTP协议
 * @author: spdz
 * @create: 2020-12-31 16:30
 **/

//拆分 URL 中的 信息
public class URLParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String URL = scanner.nextLine();// https://www.baidu.com:88/s?wd=java
            //parseUrl1(URL);
            parseUrl(URL);
        }

    }

    private static Map<String,Integer> defaultPort = new HashMap<>();
    static {
        defaultPort.put("https",443);
        defaultPort.put("http",80);
    }

    private static void parseUrl1(String url) {
        String[] arr1 = url.split("://");
        System.out.println("协议：" + arr1[0]);
        String[] arr2 = arr1[1].split(":");
        if (arr2.length == 1) {
            String[] arr3 = arr2[0].split("/",2);
            System.out.println("主机：" + arr3[0]);
            System.out.println("端口：" + defaultPort.get(arr1[0]));
            String[] arr4 = arr3[1].split("\\?");
            System.out.println("路径：/" + arr4[0]);
            System.out.println("查询字符串：" + arr4[1]);
        }else {
            System.out.println("主机：" + arr2[0]);
            String[] arr3 = arr2[1].split("/",2);
            System.out.println("端口：" + arr3[0]);
            String[] arr4 = arr3[1].split("\\?");
            System.out.println("路径：/" + arr4[0]);
            System.out.println("查询字符串：" + arr4[1]);
        }
    }

    private static void parseUrl(String url) {
        // 1. 在 url 中定位 协议部分 —— 通过第一个 ://
        int pos;
        pos = url.indexOf("://");
        String schema = url.substring(0, pos);
        System.out.println("协议部分: " + schema);

        String remain = url.substring(pos + 3); // 3 是跳过 ://

        pos = remain.indexOf("/");
        String service = remain.substring(0, pos);
        System.out.println("服务端: " + service);

        // 通过 service，找到 host 和 port 部分
        int i = service.indexOf(":");
        String host = null;
        int port = -1;
        if (i == -1) {
            // 没有明确 port，则 port 信息使用协议的默认信息
            // service 本身就是一个 host
            host = service;
            port = defaultPort.get(schema);
        } else {
            // 代表 service 由 host 和 port 组成
            host = service.substring(0, i);
            port = Integer.parseInt(service.substring(i + 1));
        }
        System.out.println("服务端主机信息: " + host);
        System.out.println("服务端端口信息: " + port);

        remain = remain.substring(pos);     // 包含最开始的 /
        pos = remain.indexOf("?");// ? 代表查询字符串的开始
        if (pos == -1) {
            System.out.println("路径部分: " + remain);
        } else {
            String path = remain.substring(0, pos);
            String queryString = remain.substring(pos + 1);
            System.out.println("路径部分: "+ path);
            System.out.println("查询字符串部分: " + queryString);
        }
    }
}
