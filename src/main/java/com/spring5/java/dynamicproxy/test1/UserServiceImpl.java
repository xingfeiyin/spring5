package com.spring5.java.dynamicproxy.test1;

/**
 * @author yinxf
 * @Date 2020/9/15
 * @Description
 **/
public class UserServiceImpl implements UserService {
    public void query() {
        System.out.println("查询用户信息");
    }
}
