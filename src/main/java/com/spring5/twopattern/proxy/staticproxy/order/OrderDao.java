package com.spring5.twopattern.proxy.staticproxy.order;

/**
 * @author yinxf
 * @date 2020-04-11
 */
public class OrderDao {
    public int insert(Order order){
        System.out.println("orderDao创建order成功");
        return 1;
    }
}
