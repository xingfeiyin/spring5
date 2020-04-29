package com.spring5.twopattern.prototype.deep;

import java.io.Serializable;

/**
 * @author yinxf
 * @Date 2020/4/9
 * @Description
 **/
public class JinGuBang implements Serializable {
    public float h = 100;
    public float d = 10;
    public void big(){
        this.d *= 2;
        this.h *= 2;
    }

    public void small(){
        this.d /= 2;
        this.h /= 2;
    }
}
