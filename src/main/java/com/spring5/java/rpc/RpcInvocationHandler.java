package com.spring5.java.rpc;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yinxf
 * @Date 2020/9/28
 * @Description 为处理代理流程的类
 **/

public class RpcInvocationHandler implements InvocationHandler {
    private String host;
    private int port;
    public RpcInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("客户端开始封装参数【RpcRequest】");
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameterTypes(method.getParameterTypes());
        rpcRequest.setParameters(args);

        System.out.println("客户端开始获取传输工具【tcpTransport】");
        TcpTransport tcpTransport = new TcpTransport(host,port);

        System.out.println("客户端开始发送请求参数【RpcRequest】");
        return tcpTransport.send(rpcRequest);
    }
}
