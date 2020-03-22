package com.kola.java.lesson29;

import com.kola.java.lesson28.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
反射：
1，概念：java反射机制，是在运行状态中，对于任何一个类，都能够知道这个类的所有属性方法；对于任意一个对象，都能够调用它的任意一个方法
和属性；这种动态获取信息以及动态调用方法的功能，称为java语言的反射机制
2，反射的本质：根据类的字节码class文件获取一个类的细节，包括构建出来，通过对象去调用方法，操作属性
3,反射的实现
1）获取类的字节码
2）通过字节码去创建对象
3）通过字节码获取到可以调用的方法
4）反射调用
 */
public class Reflect {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //在jvm中就会有这个类的字节码文件(.class)，存在一个唯一的对应的类字节码对象，获得方式如下：
        // 方法1：
        Class.forName("com.kola.java.lesson28.Student");
        //方法2：类名.class
        Class clazz = Student.class;
        // 方法3：对象名.getClass
        Student student = new Student();
        Class stuClass = student.getClass();
        // 字节码对象在内存中仅只存在一份，所以比较的结果是true
        System.out.println(clazz == stuClass);
        // 获得各个属性，方法
        System.out.println(clazz.getName());   // 获得
        System.out.println(clazz.getSimpleName());  //获得类名
        // 拿到类的细节进行映射--》函数，字段，构造方法--java中间的类对象
        // 获得student中间的setName方法
        Method method = clazz.getMethod("setName", String.class);
        // 反射调用方法
        method.invoke(student, "jack");
        System.out.println(student.getName());

    }
}
