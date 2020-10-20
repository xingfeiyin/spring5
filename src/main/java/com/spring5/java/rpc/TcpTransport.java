package com.spring5.java.rpc;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

/**
 * @author yinxf
 * @Date 2020/9/28
 * @Description 网络传输工具封装
 **/
public class TcpTransport {

    private String host;

    private int port;

    public TcpTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Socket newSocketInstance(){
        Socket socket ;
        try {
            socket = new Socket(host,port);
            System.out.println("[{}] 客户端新建连接:服务端地址=【{}】" + LocalDateTime.now()+ socket.getRemoteSocketAddress());
            return socket;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("客户端连接失败");
        }
    }

    public Object send(RpcRequest rpcRequest){
        Socket socket = null;
        try {
            socket = newSocketInstance();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            System.out.println("[{}] 请求参数序列化完毕"+LocalDateTime.now());

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object obj = objectInputStream.readObject();
            objectOutputStream.close();
            objectInputStream.close();
            System.out.println("[{}] 请求结果接收完毕" + LocalDateTime.now());
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("RPC 调用异常");
        }finally {
            try {
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
