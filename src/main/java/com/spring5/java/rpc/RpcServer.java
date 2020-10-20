package com.spring5.java.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yinxf
 * @Date 2020/9/28
 * @Description 服务端接口监听
 **/
public class RpcServer {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void publishServer(final Map<String,Object> serviceRegistCenter,int port){
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务端启动监听，端口=[{}]"+port);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("服务端监听到新链接，客户端地址=【{}】"+socket.getRemoteSocketAddress());
                executorService.execute(new RpcServerProcessor(socket,serviceRegistCenter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
