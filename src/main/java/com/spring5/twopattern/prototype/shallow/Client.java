package com.spring5.twopattern.prototype.shallow;

/**
 * @author yinxf
 * @Date 2020/4/9
 * @Description
 **/
public class Client {

    private Prototype prototype;

    public Client(Prototype prototype) {
        this.prototype = prototype;
    }

    public Prototype startClone(Prototype concretePrototype){
        return concretePrototype.clone();
    }
}
