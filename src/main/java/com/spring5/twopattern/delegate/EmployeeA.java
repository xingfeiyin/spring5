package com.spring5.twopattern.delegate;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class EmployeeA implements IEmployee {
    public void doing(String command) {
        System.out.println("我是A员工，开始干"+command+"的工作");
    }
}
