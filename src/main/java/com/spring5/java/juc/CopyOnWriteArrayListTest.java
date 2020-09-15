package com.spring5.java.juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author yinxf
 * @Date 2020/8/28
 * @Description
 **/
public class CopyOnWriteArrayListTest {
    /**
     * 验证CopyOnWriteArrayList使用迭代器遍历时，弱一致性问题
     * 先获取Iterator，然后从集合中删除两个元素，遍历Iterator，还显示所有元素
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        //获取快照信息
        Iterator<String> iterator = list.iterator();

        //删除list中的元素
        new Thread(() -> {
            list.remove(1);
            list.remove(3);
        }).start();
        TimeUnit.SECONDS.sleep(3);

        //遍历快照集合
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
