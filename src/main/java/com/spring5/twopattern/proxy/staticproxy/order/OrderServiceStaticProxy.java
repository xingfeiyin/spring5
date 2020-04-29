package com.spring5.twopattern.proxy.staticproxy.order;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yinxf
 * @date 2020-04-11
 * 创建切换数据源的代理类OrderServiceStaticProxy
 */
public class OrderServiceStaticProxy implements IOrderService {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    private IOrderService orderService;

    public OrderServiceStaticProxy(IOrderService orderService) {
        this.orderService = orderService;
    }

    public int createOrder(Order order) {
        before();
        Long time = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(sdf.format(new Date(time)));
        System.out.println("静态代理自动分配到【DB_"+dbRouter+"】数据源处理数据");
        DynamicDataSourceEntry.set(dbRouter);
        orderService.createOrder(order);
        after();
        return 0;
    }

    private void after() {
        System.out.println("proxy after method");
    }

    private void before() {
        System.out.println("proxy before method.");
    }
}
