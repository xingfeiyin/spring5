package com.spring5.twopattern.proxy.staticproxy.order;

/**
 * @author yinxf
 * @date 2020-04-11
 */
public class OrderService implements IOrderService {

    private OrderDao orderDao;

    public OrderService() {
        //如果使用spring应该是自动注入的
        //为了使用方便，我们在构造函数中将orderDao直接初始化
        this.orderDao = new OrderDao();
    }

    public int createOrder(Order order) {
        System.out.println("orderService调用orderDao创建订单");
        return orderDao.insert(order);
    }
}
