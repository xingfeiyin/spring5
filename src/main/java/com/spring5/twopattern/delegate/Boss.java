package com.spring5.twopattern.delegate;

/**
 * @author yinxf
 * @date 2020-04-25
 */
public class Boss {
    public void command(String command,Leader leader){
        leader.doing(command);
    }
}
