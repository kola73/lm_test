package com.kola.java.lesson13;

public class Function {
    public static int sum(int a, int b) {
        return a + b;
    }

    public static void sayHello() {
        System.out.println("大家好");
    }

    public static void main(String[] args) {
        int result = sum(1, 2);
        System.out.println(result);
        sayHello();
    }
}
