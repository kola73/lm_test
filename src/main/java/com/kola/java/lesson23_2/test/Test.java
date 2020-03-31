package com.kola.java.lesson23_2.test;

import main.java.com.kola.java.lesson23_2.AccessModifier;

public class Test {
    public static void main(String[] args) {
        AccessModifier ac=new AccessModifier();
        System.out.println(ac.name);
//        System.out.println(ac.id); //protected：不同包无法访问
//        System.out.println(ac.phone);  default:只支持本类，同包
    }
}
