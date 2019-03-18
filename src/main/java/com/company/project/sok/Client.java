package com.company.project.sok;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
//    public static void main(String[] args) {
//       a();
//    }
    
    private static void a() {
        try {

            // 创建客户端Socket,指定服务器地址和端口
//            Socket socket = new Socket("47.102.50.208", 3001);
            Socket socket = new Socket("106.15.131.133", 13001);
//            Socket socket = new Socket("106.14.93.168", 13001);
           
            // 获取输出流,向服务器端发送信息
            OutputStream outputStream = socket.getOutputStream(); // 字节输出流
            PrintWriter printWriter = new PrintWriter(outputStream); // 将输出流包装为打印流
            // 获取客户端的IP地址
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();
            printWriter.write("客户端 : " + ip + ", 接入服务器。");
            printWriter.flush();
            socket.shutdownOutput(); // 关闭输出流
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
