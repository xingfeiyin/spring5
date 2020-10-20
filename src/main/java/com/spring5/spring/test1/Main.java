package com.spring5.spring.test1;

import com.spring5.spring.test1.Service;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yinxf
 * @Date 2020/9/17
 * @Description  spring  register
 **/
public class Main {
    public static void main(String[] args) {
        register();

        print();

        registerBean();

        print();

        beanPostProcessor();

        print();

        beanFactoryPostProcessor();
    }

    /**
     * 文件  *3
     * spring BeanFactoryPostProcessor
     * 实现它可以读取bean定义，然后对其进行修改，比如修改bean的作用域
     * BeanFactoryPostProcessor 在BeanPostProcessor之前
     *
     */
    private static void beanFactoryPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig2.class);
        System.out.println(context.getBeanDefinition("repo3").getScope());
    }

    /**
     * 文件  *2
     * spring BeanPostProcessor 通过实现它，插手bean的实例化过程，在bean创建前后做一些事情
     * spring内部其实也是通过BeanPostProcessor接口来完成动态代理的。
     */
    private static void beanPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig2.class);
        context.getBean(Service2.class).query();
    }

    /**
     * spring registerBean
     *  可以不用在bean类上打各种注解
     */
    private static void registerBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean("myService",Service1.class,()-> new Service1("hello"),z->{
            z.setScope("prototype");
        });
        context.refresh();
        System.out.println(context.getBean("myService").getClass().getSimpleName());
        System.out.println(context.getBeanDefinition("myService").getScope());
    }

    /**
     * spring register
     */
    private static void register() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Service.class);
        context.refresh();
        System.out.println(context.getBean(Service.class).getClass().getSimpleName());
    }

    public static void print(){
        System.out.println("****************************************************");
    }
}
