package com.spring5.springcore7;

import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;

import javax.print.DocFlavor;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @author yinxf
 * @date 2020-04-29
 */
public class GPDispatcherServlet extends HttpServlet {
    private Map<String,Object> mapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req,resp);
        }catch (Exception e){
            resp.getWriter().write("500 Exception "+ Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        //1.0版本
//        String url = req.getRequestURI();
//        String contextPath = req.getContextPath();
//        url = url.replace(contextPath,"").replaceAll("/+","/");
//        if (!this.mapping.containsKey(url)){
//            resp.getWriter().write("404 Not Found!");
//            return;
//        }
//
//        Method method = (Method) this.mapping.get(url);
//        Map<String,String[]> params = req.getParameterMap();
//        method.invoke(this.mapping.get(method.getDeclaringClass().getName()),new Object[]{req,resp,params.get("name")[0]});

        //2.0版本
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath,"").replaceAll("/+","/");
        if (!this.mapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!");
            return;
        }

        Method method = (Method) this.mapping.get(url);
        Map<String,String[]> params = req.getParameterMap();
        //投机取巧的方式
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName),new Object[]{req,resp,params.get("name")[0]});
    }

    /**
     * 2.0版本
     */
    //保存application.properties配置文件中的内容
    private Properties contextConfig = new Properties();
    //保存扫描的所有类
    private List<String> classNames = new ArrayList<>();
    //传说中的IOC容器，我们来揭开它的神秘面纱，
    //为了简化程序，暂时不考虑ConcurrentHashMap
    //主要还是关注设计思想和原理
    private Map<String,Object> ioc = new HashMap<>();
    //保存URL和method的关系
    private Map<String,Method> handlerMapping = new HashMap<>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        //加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scaPackage"));

        //3.初始化扫描到的类，并且将它们放入IOC容器
        doInstance();

        //4.完成依赖注入
        doAutowired();

        //5.初始化handlermapping
        initHandlerMapping();

        System.out.println("GP spring framework is init.");
    }

    //策略模式的应用案例
    private void initHandlerMapping() {
        if (ioc.isEmpty()){return;}

        for (Map.Entry<String,Object> entry : ioc.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(GPController.class)){continue;}

            //保存写在类上面的@GPRequestMapping("/demo")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(GPRequestMapping.class)){ //如果有注解GPRequestMapping获取它的value
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //默认获取所有类型的public方法
            for (Method method : clazz.getMethods()){
                if (!method.isAnnotationPresent(GPRequestMapping.class)){continue;}

                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                //优化
                String url = ("/" +baseUrl +"/" +requestMapping.value()).replaceAll("/+","/");
                handlerMapping.put(url,method);
                System.out.println("mapped:"+url +"," +method);
            }
        }

    }

    //自动进行依赖注入
    private void doAutowired() {
        if (ioc.isEmpty()){return;}

        for (Map.Entry<String,Object> entry : ioc.entrySet()){
            //根据所有的字段，包括private、protected、default类型的
            //正常来说，普通的OOP编程只能获得public类型的字段
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(GPAutowired.class)){continue;}
                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                //如果用户没有自定义的beanname，默认就根据类型注入
                String beanName = autowired.value().trim();
                if ("".equals(beanName)){
                    //获得接口的类型，作为key,稍后用这个key到IOC中取值
                    beanName = field.getType().getName();
                }
                //如果是public以外的类型，只要加了@autowired注解都要强制赋值
                //反射中叫暴力赋值
                field.setAccessible(true);

                try {
                    //用反射机制动态给字段赋值
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //doInstance方法就是工厂模式的具体体现
    private void doInstance() {
        if (classNames.isEmpty()){return;}
        try {
            for (String className : classNames){
                Class<?> clazz = Class.forName(className);
                //什么样的类才需要初始化呢？
                //加了注解的类才初始化，怎么判断？
                //为了简化代码逻辑，主要体会设计思想，只用@Controller和@service举例，
                //@componment等就不一一举例了
                if (clazz.isAnnotationPresent(GPController.class)){
                    Object instance = clazz.newInstance();
                    //spring默认类名首字母小写，
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName,instance);
                }else if (clazz.isAnnotationPresent(GPService.class)){
                    //1.自定义的beanName
                    GPService service = clazz.getAnnotation(GPService.class);
                    String beanName = service.value();
                    if ("".equals(beanName.trim())){
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);
                    //3.根据类型自动赋值，这是投机取巧的方式
                    for (Class<?> i : clazz.getInterfaces()){
                        if (ioc.containsKey(i.getName())){
                            throw new Exception("the "+i.getName() +" is exists!");
                        }
                        //把接口的类型直接当成key
                        ioc.put(i.getName(),instance);
                    }
                }else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //为了方便处理，自己实现了toLowerFirstCase方法，来实现类名首字母小写
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //之所以要做加法，是因为大小写字母的ASCII码相差32
        //大写字母的ASCII码小于小写字母的ASCII码
        //在java中，对char做算术运算实际就是对ASCII码进行算术运算
        chars[0] += 32 ;
        return String.valueOf(chars);
    }

    //加载配置文件
    private void doLoadConfig(String contextConfigLocation) {
        //直接通过类路径找到spring主配置文件所在的路径
        //并且将其读取出来放到properties对象中
        //相当于将scanPackage=com.spring5.springcore7保存到了内存
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 1.0版本
     */
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        InputStream is = null;
//        try {
//              //加载配置文件
//            Properties configContext = new Properties();
//            is = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
//            configContext.load(is);
//            String scaPackage = configContext.getProperty("scanPackage");
//            doScanner(scaPackage);
//            for (String className : mapping.keySet()){
//                if (!className.contains(".")){continue;}
//                Class<?> clazz = Class.forName(className);
//                if (clazz.isAnnotationPresent(GPController.class)){
//                    mapping.put(className,clazz.newInstance());
//                    String baseUrl = "";
//                    if (clazz.isAnnotationPresent(GPRequestMapping.class)){
//                        GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
//                        baseUrl = requestMapping.value();
//                    }
//                    Method[] methods = clazz.getMethods();
//                    for (Method method : methods) {
//                        if (!method.isAnnotationPresent(GPRequestMapping.class)){continue;}
//                        GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
//                        String url = (baseUrl + "/" +requestMapping.value()).replaceAll("/+","/");
//                        mapping.put(url,method);
//                        System.out.println("mapped " +url +"," + method);
//                    }
//                }else if (clazz.isAnnotationPresent(GPService.class)){
//                    GPService service = clazz.getAnnotation(GPService.class);
//                    String beanName = service.value();
//                    if ("".equals(beanName)){beanName = clazz.getName();}
//                    Object instance = clazz.newInstance();
//                    mapping.put(beanName,instance);
//                    for (Class<?> i : clazz.getInterfaces()){
//                        mapping.put(i.getName(),instance);
//                    }
//                }else {
//                    continue;
//                }
//            }
//              //完成依赖注入
//            for (Object object : mapping.values()){
//                if (object == null){continue;}
//                Class clazz = object.getClass();
//                if (clazz.isAnnotationPresent(GPController.class)){
//                    Field[] fields = clazz.getFields();
//                    for (Field field : fields) {
//                        if (!field.isAnnotationPresent(GPAutowired.class)){continue;}
//                        GPAutowired autowired = field.getAnnotation(GPAutowired.class);
//                        String beanName = autowired.value();
//                        if ("".equals(beanName)){beanName = field.getType().getName();}
//                        field.setAccessible(true);
//                        try {
//                            field.set(mapping.get(clazz.getName()),mapping.get(beanName));
//                        }catch (IllegalAccessException e){
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }catch (Exception e){
//
//        }finally {
//            if (is != null){
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("GP mvc framework is init");
//    }




    private void doScanner(String scaPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scaPackage.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()){
            if (file.isDirectory()){
                doScanner(scaPackage + "." +file.getName());
            }else {
                if (!file.getName().endsWith(".class")){continue;}
                String className = (scaPackage +"."+file.getName().replace(".class",""));
                mapping.put(className,null);
            }
        }
    }
}
