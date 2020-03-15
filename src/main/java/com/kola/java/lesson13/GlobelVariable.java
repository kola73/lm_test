package com.kola.java.lesson13;

/**
 * 全局变量
 */
public class GlobelVariable {
    static int jackAge = 3;  //整个类都可以引用
    int tomAge;
    static int lucyAge;
    static int[] ages;

    // 静态方法只能调用静态的参数
    public static void main(String[] args) {
        System.out.println(jackAge);
        System.out.println(lucyAge);  //可以不实例化，就给默认的值
//        System.out.println(ages.length); // 没有定义长度的话，会报空指针
    }

    // 非静态方法可以调用静态和非静态的参数
    public void test() {
        System.out.println(tomAge);
        System.out.println(jackAge);
    }
}
