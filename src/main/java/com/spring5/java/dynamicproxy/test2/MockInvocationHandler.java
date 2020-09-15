package com.spring5.java.dynamicproxy.test2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yinxf
 * @Date 2020/9/15
 * @Description
 **/
public class MockInvocationHandler {

    private Object targetObject;

    public MockInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    public void invoke(Method targetMethod){
        try {
            System.out.println("进入了invoke");
            targetMethod.invoke(targetObject,null);
            System.out.println("结束了invoke");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
