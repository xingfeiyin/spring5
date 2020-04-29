package com.spring5.twopattern.proxy.dynamicproxy.cglibproxy;

import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @author yinxf
 * @Date 2020/4/23
 * @Description
 * CGlib代理执行代理方法的效率之所以比JDK高，是因为CGLib采用了FastClass机制，它的原理简单来说就是：
 *  为代理类和被代理类各生成一个类，这个类会为代理类或被代理类的方法分配一个index(int类型)；这个index当做一个入参，
 *  FastClass就可以直接定位要调用的方法并直接进行套用，省去了反射调用，所以利用效率比JDK代理通过反射调用高。
 *
 * FastClass 并不是跟代理类一起生成的，而是在第一次执行MethodProxy的invoke()或invokeSuper()方法时生成的，并放在了缓存中。
 *
 **/
public class CglibTest {
    public static void main(String[] args) {
        try {
            //利用CGlib的代理类可以将内存中的.class文件写入本地磁盘
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D://cglib_proxy_class");

            Customer obj = (Customer) new CglibMeiPo().getInstance(Customer.class);
            obj.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
