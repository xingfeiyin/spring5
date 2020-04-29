package com.spring5.twopattern.prototype.shallow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinxf
 * @Date 2020/4/9
 * @Description 浅克隆只是完整复制了值的类型数据，没有赋值引用对象。换言之，所有的引用对象仍然指向原来的对象，
 *              显然不是我们想要的结果。
 **/
public class PrototypeTest {
    public static void main(String[] args) {
        //创建一个具体的需要克隆的对象
        ConcretePrototypeA concretePrototype = new ConcretePrototypeA();
        concretePrototype.setAge(18);
        concretePrototype.setName("prototype");
        List hobbies = new ArrayList();
        concretePrototype.setHobbies(hobbies);
        System.out.println(concretePrototype);

        //创建client对象准备克隆
        Client client = new Client(concretePrototype);
        ConcretePrototypeA concretePrototypeClone = (ConcretePrototypeA) client.startClone(concretePrototype);
        System.out.println(concretePrototypeClone);

        System.out.println("克隆对象中的引用地址值："+concretePrototypeClone.getHobbies());
        System.out.println("原对象中的引用地址值："+concretePrototype.getHobbies());
        System.out.println("对象地址比较：" +(concretePrototype.getHobbies() == concretePrototypeClone.getHobbies()));


    }
}
