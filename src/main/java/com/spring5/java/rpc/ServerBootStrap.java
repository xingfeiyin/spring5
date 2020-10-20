package com.spring5.java.rpc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yinxf
 * @Date 2020/9/28
 * @Description
 **/
public class ServerBootStrap {
    public static void main(String[] args) {
        //注册中心
        Map<String,Object> registCenter = new HashMap<>(16,1);
        //实例构建
        IUserService userService = new OtherUserService();
        registCenter.put(IUserService.class.getName(),userService);
        RpcServer rpcServer = new RpcServer();
        System.out.println("[{}] 服务端发布对外服务【IUserService】"+ LocalDateTime.now());
        rpcServer.publishServer(registCenter,8888);
    }
}
