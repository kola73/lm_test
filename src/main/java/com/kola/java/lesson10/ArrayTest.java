package com.kola.java.lesson10;

/**
 * 数组
 * 索引从0开始
 */
public class ArrayTest {
    // 一维数组
    public static void main(String[] args) {
        // 指定长度
        int[] intArr = new int[5];
        // 赋值
        intArr[0] = 1;
        intArr[1] = 3;
        System.out.println(intArr[1]);
        // 也可以声明时候就直接给出具体的值
        int intArr2[] = {1, 2, 3};
        System.out.println(intArr2[2]);
    }

}
