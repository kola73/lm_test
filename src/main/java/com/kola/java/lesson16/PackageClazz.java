package com.kola.java.lesson16;

/**
 * 包装类：以类的形式来管理基本数据类型
 * 为了调节java一切皆为对象的概念，有了包装类
 * 矛盾：基本数据类型不是类
 * 解决矛盾：8个基本数据类型有8个包装类
 * 8种类型的除了int（Integer），char（Character），其他类型的包装类都只要改成首字母大写就行了，比如short(Short）
 */
public class PackageClazz {
    public static void main(String[] args) {
        Integer i = 20;  // 自动装箱
        System.out.println(i);  // 自动拆箱
        // 包装类应用场景：1，获得最大最小值
        System.out.println(Integer.MAX_VALUE);
        // 手动装箱
        Integer j = new Integer(3);
        // 手动拆箱
        System.out.println(j.intValue());
        System.out.println("=======");
        // 2，把数值对应的字符转换为基本数据类型（接收可以用包装类，也可以用普通的类型进行接收）
        String str = "234";
        int i1 = Integer.parseInt(str);
        System.out.println(i1);
        String s = "3.42";
        Double d = Double.parseDouble(s);
        System.out.println(d);
    }
}
