package com.spring5.springcore7;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yinxf
 * @date 2020-04-29
 */
@GPController
@GPRequestMapping("/demo")
public class DemoAction {
    @GPAutowired
    private IDemoService demoService;

    @GPRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp,
                      @GPRequestParam("name") String name){
        String result = demoService.get(name);
        try {
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GPRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                      @GPRequestParam("a") Integer a,@GPRequestParam("b") Integer b){
        try {
            resp.getWriter().write(a+ " + " + b + "=" +(a+b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GPRequestMapping("/remove")
    public void remove(HttpServletRequest req, HttpServletResponse resp,
                    @GPRequestParam("id") Integer id){

    }
}
