package com.kola.interface_auto.lesson51.Utils;

// 判断字符串是否为空
public class StringUtils {
    public static boolean isEmpty(String str) {
        return (str == null) || ("".equals(str.replace(" ", "")));
    }

    // test
    public static void main(String[] args) {
        String s1 = null;
        String s2 = " ";
        String s3 = "";
        String s4 = " a ";
        System.out.println(isEmpty(s1));
        System.out.println(isEmpty(s2));
        System.out.println(isEmpty(s3));
        System.out.println(isEmpty(s4));
    }
}
