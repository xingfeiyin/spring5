package com.spring5.twopattern.proxy.dynamicproxy;

import com.spring5.twopattern.proxy.staticproxy.father.Person;
import com.spring5.twopattern.proxy.staticproxy.order.IOrderService;
import com.spring5.twopattern.proxy.staticproxy.order.Order;
import com.spring5.twopattern.proxy.staticproxy.order.OrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yinxf
 * @date 2020-04-11
 */
public class Main {
    public static void main(String[] args) {
//        method1();

        try {
            Order order = new Order();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
            Date date = sdf.parse("2018/02/01");
            order.setCreateTime(date.getTime());

            IOrderService orderService = (IOrderService) new OrderServiceDynamicProxy().getInstance(new OrderService());
            orderService.createOrder(order);


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void method1() {
        try {
            Person person = (Person) new JDKMeiPo().getInstance(new Customer());
            person.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
