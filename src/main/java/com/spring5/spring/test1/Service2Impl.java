package com.spring5.spring.test1;

import org.springframework.stereotype.Component;

/**
 * @author yinxf
 * @Date 2020/9/17
 * @Description
 **/
@Component
public class Service2Impl implements Service2 {
    @Override
    public void query() {
        System.out.println("正在查询中。。。。。");
    }
}
