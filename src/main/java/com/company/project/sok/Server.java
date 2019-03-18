package com.company.project.sok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server extends Thread {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private int          port   = 4005;
    
    @Override
    public void run() {
        try {
            if (port > 0) {
                ServerSocket server = new ServerSocket(port);
                // 2.调用 accept 方法开始监听，等待客户端连接
                logger.info("****服务器开始启动****");
                while (true) {
                    try {
                        dopost(server);
                    } catch (Exception e) {
                        logger.error("启动socket监听失败e{}", e);
                    }
                }
            } else {
                logger.equals("port is null");
            }
        } catch (Exception e) {
            logger.error("启动socket服务失败e{}", e);
        }
    }

    private void dopost(ServerSocket server) throws Exception {
        // 1.创建一个服务器端的 Socket，即 ServerSocket，指定绑定的端，并监听
        logger.info("****等待客户端上线****");
        Socket socket = null;
        try {
            socket = server.accept();
            logger.info("----999");
        } catch (Exception e) {
            logger.error("等待异常e:",e);
            if (socket !=null) {
                socket.shutdownOutput();
            }
        }
        System.out.println("new connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
        logger.info("new connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
        logger.info("getRemoteSocketAddress " + socket.getRemoteSocketAddress() );
        logger.info("000");
        // 3.获取一个输入流，用来读取客户端所发送的登录信息
        InputStream is = socket.getInputStream();// 字节输入流
        logger.info("111");
        InputStreamReader isr = new InputStreamReader(is, "GBK");// 将字节流转为
        logger.info("222");
        BufferedReader br = new BufferedReader(isr);// 为输入流添加缓冲
        logger.info("333");
        String info = null;
        while((info=br.readLine())!=null){//循环读取客户端的信息
            System.out.println("我是服务器，客户端说：" + info);
        }
        logger.info("444");
//        char[] cbuf = new char[54];
//        br.read(cbuf);
//        String str = String.valueOf(cbuf);
//        System.out.println("前54位：-----------" + str);
//        String len = str.substring(48, 52);
//        int length = Integer.parseInt(len);
//        char[] c = new char[length];
//        br.read(c);
//        String info = String.valueOf(c);
//        System.out.println("54位后：-----------" + info);
        logger.info("****输出数据****" + info);
        socket.shutdownInput();// 关闭输入流
        logger.info("555");
        // 4.获取输出流
//        OutputStream os = socket.getOutputStream();
//        OutputStreamWriter outSW = new OutputStreamWriter(os, "GBK");
//        BufferedWriter bw = new BufferedWriter(outSW);
//        bw.write("aa");
//        bw.flush();
//        socket.shutdownOutput();
        // 5.关闭资源
//        bw.close();
        br.close();
        logger.info("666");
        isr.close();
        logger.info("777");
        is.close();
        logger.info("888");
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}