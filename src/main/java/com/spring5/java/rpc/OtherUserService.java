package com.spring5.java.rpc;

/**
 * @author yinxf
 * @Date 2020/9/28
 * @Description 提供方实现
 **/
public class OtherUserService implements IUserService {

    @Override
    public String findNameById(Long id) {
        return "other name " + id;
    }
}
