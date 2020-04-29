package com.spring5.twopattern.singleton.register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author yinxf
 * @date 2020-04-08
 */
public class EnumSingletonTest {
    public static void main(String[] args) {
        try {
            EnumSingleton singleton1 = null;
            EnumSingleton singleton2 = EnumSingleton.getInstance();
            singleton2.setData(new Object());
            FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(singleton2);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("EnumSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            singleton1 = (EnumSingleton) ois.readObject();
            ois.close();

            System.out.println(singleton1);
            System.out.println(singleton2);
            System.out.println(singleton1 == singleton2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
