package com.spring5.twopattern.proxy.staticproxy.order;

/**
 * @author yinxf
 * @date 2020-04-11
 * 静态代理主要功能是：根据订单创建时间自动按年进行分库。
 * 通过代理类来完成，先创建数据源路由对象，使用Threadlocal的单例实现DynamicDataSourceEntry类
 *
 */
//动态切换数据源
public class DynamicDataSourceEntry {
    //默认数据源
    private final static String DEFAULT_SOURCE = null;

    private final static ThreadLocal<String> local = new ThreadLocal<String>();

    public DynamicDataSourceEntry() {
    }

    //清空数据源
    public static void clear(){
        local.remove();
    }

    //获取当前正在使用的数据源名字
    public static String get(){
        return local.get();
    }

    //还原当前切换的数据源
    public static void restore(){
        local.set(DEFAULT_SOURCE);
    }

    //设置已知名字的数据源
    public static void set(String source){
        local.set(source);
    }

    //根据年份动态设置数据源
    public static void set(int year){
        local.set("DB_"+year);
    }
}
