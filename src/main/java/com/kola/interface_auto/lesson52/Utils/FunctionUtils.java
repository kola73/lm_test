package com.kola.interface_auto.lesson52.Utils;

/*
方法参数化；
因为一些值比如密码，很多都是md5加密
excel里把密码改成："password":"__md5(123456a)"，表示从functionUtils类里调用md5方法，传入参数，最终返回md5的值，然后去替换
 */
public class FunctionUtils {
    public static String md5(String str) {
        return "efc4fab2ae3f7ef3bf9addee5458e7b21906ed07";
    }

    public static String getUserName() {
        return "admin";
    }
}
