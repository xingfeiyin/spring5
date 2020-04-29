package com.spring5.twopattern.strategy.pay;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class PayStrategyTest {
    public static void main(String[] args) {
        Order order = new Order("1","2020042512530000",324.25);
        System.out.println(order.pay(PayStrategy.ALI_PAY));
    }
}
