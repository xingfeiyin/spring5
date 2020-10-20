package com.spring5.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinxf
 * @Date 2020/10/15
 * @Description
 **/
public class HeapOOM {
    static class OOM{}

    public static void main(String[] args) {
        List<OOM> list = new ArrayList<>();
        while (true){
            list.add(new OOM());
        }
    }
}
