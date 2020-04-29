package com.spring5.twopattern.proxy.staticproxy.father;

/**
 * @author yinxf
 * @date 2020-04-11
 */
public class Father {
    private Son son;

    public Father(Son son) {
        this.son = son;
    }
    //获取目标对象的引用
    public void findLove(){
        System.out.println("父亲物色对象");
        this.son.findLove();
        System.out.println("双方同意交往，确立关系");
    }
}
