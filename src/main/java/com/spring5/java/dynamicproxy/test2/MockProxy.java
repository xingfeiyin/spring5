package com.spring5.java.dynamicproxy.test2;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author yinxf
 * @Date 2020/9/15
 * @Description
 **/
public class MockProxy {

    final static String ENTER = "\n";
    final static String TAB = "\t" ;

    public static Object newProxyInstance(Class interfaceClass,MockInvocationHandler h){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package com.spring5.java.dynamicproxy.test2;");
        stringBuilder.append(ENTER);
        stringBuilder.append("import java.lang.reflect.*;");
        stringBuilder.append(ENTER);
        stringBuilder.append("public class $Proxy implements " + interfaceClass.getName() +" {");
        stringBuilder.append(ENTER);
        stringBuilder.append(TAB);
        stringBuilder.append(" MockInvocationHandler h;");
        stringBuilder.append(ENTER);
        stringBuilder.append(TAB);
        stringBuilder.append("public $Proxy(MockInvocationHandler h){ this.h = h;}");
        stringBuilder.append(ENTER);
        stringBuilder.append(TAB);
        for(Method method : interfaceClass.getMethods()){
            stringBuilder.append("public void " + method.getName() + "(){");
            stringBuilder.append(ENTER);
            stringBuilder.append(TAB);
            stringBuilder.append(" try{");
            stringBuilder.append(ENTER);
            stringBuilder.append(TAB);
            stringBuilder.append(TAB);
            stringBuilder.append(" Method method = " +interfaceClass.getName() +".class.getMethod(\""+method.getName()+"\");");
            stringBuilder.append(ENTER);
            stringBuilder.append(TAB);
            stringBuilder.append(TAB);
            stringBuilder.append("this.h.invoke(method);");
            stringBuilder.append(ENTER);
            stringBuilder.append(TAB);
            stringBuilder.append(TAB);
            stringBuilder.append("}catch(Exception ex){}");
            stringBuilder.append(ENTER);
            stringBuilder.append(TAB);
            stringBuilder.append("}");
            stringBuilder.append(ENTER);
            stringBuilder.append("}");
        }
        String content = stringBuilder.toString();
        try {
            String filePath = "d:\\com\\spring5\\java\\dynamicproxy\\test2\\$Proxy.java";
            File file = new File(filePath);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()){
                fileParent.mkdirs();
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = fileManager.getJavaFileObjects(filePath);
            JavaCompiler.CompilationTask task = compiler.getTask(null,fileManager,null,null,null,iterable);
            task.call();
            fileManager.close();

            URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:D:\\\\")});
            Class<?> clazz = classLoader.loadClass("com.spring5.java.dynamicproxy.test2.$Proxy");
            Constructor<?> constructor = clazz.getConstructor(MockInvocationHandler.class);
            return constructor.newInstance(h);
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}
