package com.spring5.twopattern.singleton.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yinxf
 * @Date 2020/4/9
 * @Description
 **/
public class ContainerSingleton {
    private ContainerSingleton(){}

    private static Map<String ,Object> ioc = new ConcurrentHashMap<String, Object>();

    public static Object getBean(String className){
        synchronized (ioc){
            if (!ioc.containsKey(className)){
                Object object = null;
                try {
                    object = Class.forName(className).newInstance();
                    ioc.put(className,object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return object;
            }else {
                return ioc.get(className);
            }
        }
    }
}
