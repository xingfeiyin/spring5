package com.spring5.java.rpc;

import java.lang.reflect.Proxy;

/**
 * @author yinxf
 * @Date 2020/9/28
 * @Description
 **/
public class RpcClientProxy {
    public  <T>T clientProxy(final Class<T> interfaces,final String host,final int port){
        return (T)Proxy.newProxyInstance(interfaces.getClassLoader(),new Class[]{interfaces},new RpcInvocationHandler(host,port));
    }
}
