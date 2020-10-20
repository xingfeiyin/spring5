package com.spring5.java.rpc;

import java.time.LocalDateTime;

/**
 * @author yinxf
 * @Date 2020/9/28
 * @Description
 **/
public class ClientBootStrap {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy();
        IUserService userService = proxy.clientProxy(IUserService.class,"localhost",8888);
        System.out.println("[{}] 客户端开始发起RPC调用" + LocalDateTime.now());
        String name = userService.findNameById(101L);
        System.out.println(name);
    }
}
