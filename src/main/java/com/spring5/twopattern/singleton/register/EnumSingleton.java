package com.spring5.twopattern.singleton.register;

/**
 * @author yinxf
 * @date 2020-04-08
 * 枚举式单例在静态代码块中就给INSTANCE进行了赋值。
 * ObjectInputStream 类中的readObject()方法 TC_ENUM
 * 可以发现枚举类型其实是通过类名和类对象找到一个唯一的枚举对象。因此，枚举对象不可能被类加载器加载多次。
 */
public enum EnumSingleton {
    INSTANCE;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
