package com.spring5.java.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * @author yinxf
 * @Date 2020/9/28
 * @Description 服务端方法调用
 **/
public class RpcServerProcessor implements Runnable {

    private Socket socket;
    private Map<String,Object> serviceRegisteCenter;

    public RpcServerProcessor(Socket socket, Map<String, Object> serviceRegistCenter) {
        this.socket = socket;
        this.serviceRegisteCenter = serviceRegistCenter;
    }

    @Override
    public void run() {
        //获取客户端传输对象，并反序列化
        try (ObjectInputStream inputStream = new ObjectInputStream((socket.getInputStream()));
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())){

            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            System.out.println("[{}] 服务端反序列化完毕" +  LocalDateTime.now());

            Object obj = invoke(rpcRequest);
            System.out.println("[{}] 服务端调用完毕" + LocalDateTime.now());
            outputStream.writeObject(obj);
            outputStream.flush();
            System.out.println("[{}] 服务端序列化完毕——将结果发送给客户端" + LocalDateTime.now());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private Object loadBalance(String className){
        return serviceRegisteCenter.get(className);
    }
    private Object invoke(RpcRequest rpcRequest) throws Exception {
        Object[] args = rpcRequest.getParameters();
        Class<?>[] types = rpcRequest.getParameterTypes();
        String className = rpcRequest.getClassName();
        //服务发现，负载均衡
        Object service = loadBalance(className);
        //服务调用
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(),types);
        return method.invoke(service,args);
    }
}
