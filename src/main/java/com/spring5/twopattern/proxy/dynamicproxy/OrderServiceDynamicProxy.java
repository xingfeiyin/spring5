package com.spring5.twopattern.proxy.dynamicproxy;

import com.spring5.twopattern.proxy.staticproxy.order.DynamicDataSourceEntry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yinxf
 * @Date 2020/4/23
 * @Description
 **/
public class OrderServiceDynamicProxy implements InvocationHandler {
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private Object target;

    public Object getInstance(Object target){
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        Object invoke = method.invoke(target, args);
        after();
        return invoke;
    }

    private void after() {

        System.out.println("proxy after method.");
    }

    private void before(Object target) {
        try {
            System.out.println("proxy before method.");
            Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
            Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
            System.out.println("代理类自动分配到"+dbRouter +"数据源处理数据");
            DynamicDataSourceEntry.set(dbRouter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
