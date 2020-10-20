package com.spring5.java.rpc;



import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author yinxf
 * @Date 2020/9/28
 * @Description 使用方请求参数封装
 **/
@Setter
@Getter
public class RpcRequest implements Serializable {

    /**
     * 类名称
     */
    private String className ;

    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数类型列表
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数列表
     */
    private Object[] parameters;
}
