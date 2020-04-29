package com.spring5.twopattern.proxy.dynamicproxy.gpproxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yinxf
 * @Date 2020/4/23
 * @Description
 **/
public interface GPInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}
