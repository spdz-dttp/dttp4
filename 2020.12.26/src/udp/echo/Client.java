package udp.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.Scanner;

/**
 * @program: 2020.12.26
 * @description: 网络2 UDP-Socket
 * @author: spdz
 * @create: 2020-12-26 22:08
 **/

/**
 * 如何利用0S提供的网络服务，进行网络上的进程和进程之间进行通信。
 * 1. UDP (代码相对比较简单，但缺点是，可能有数据丢失的风险(不可靠) )
 * 2. TCP (如果数据比较重要，就使用更贵的服务，更可靠)
 */

public class Client {
    //127.0.0.1 指 当前进程所在的地址
    public static final String SERVER_HOST = "127.0.0.1";

    public static void main(String[] args) {
        // 从标准输入中读取内容的 scanner
        Scanner stdinScanner = new Scanner(System.in);

        // 创建一个 DatagramSocket，创建方式和 Server 的略有不同
        // 不用明确我自己的 port，交给 OS 随意分配一个未被使用的 port 即可
        // 我们是 Client，我们 地址 不需要公开
        try(DatagramSocket clientSocket = new DatagramSocket()) {
            System.out.printf("%s: DEBUG: 已经创建好 socket 对象%n", new Date());
            System.out.print("echo> ");
            while (stdinScanner.hasNextLine()) {
                String request = stdinScanner.nextLine();
                System.out.printf("%s: DEBUG: 读取用户的输入是: %s%n", new Date(), request);
                if (request.equalsIgnoreCase("quit")) {
                    break;
                }

                // 准备发送
                byte[] sendBuffer = request.getBytes("UTF-8");
                // 绑定对方的 port
                // 127.0.0.1:8260
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer,sendBuffer.length,
                        InetAddress.getByName(SERVER_HOST),Server.PORT);
                System.out.printf("%s: DEBUG: 准备发送请求 ...%n", new Date());
                clientSocket.send(sendPacket);
                System.out.printf("%s: DEBUG: 请求发送成功%n", new Date());

                // 等待响应
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,0,receiveBuffer.length);

                System.out.printf("%s: DEBUG: 准备接收响应 ...%n", new Date());
                clientSocket.receive(receivePacket);
                System.out.printf("%s: DEBUG: 接收响应成功%n", new Date());

                // 转成字符形式
                String response = new String(receiveBuffer,
                        0,receivePacket.getLength(),"UTF-8");
                System.out.println("echo service 应答: " + response);
                System.out.print("echo> ");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}