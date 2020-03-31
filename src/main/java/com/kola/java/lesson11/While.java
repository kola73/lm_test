package com.kola.java.lesson11;

/**
 * 循环结构的四大要素：
 * 1，初始化变量（条件）：定义一个变量，保存初始值
 * 2，循环条件：执行循环的一个必要条件，条件表达式为真则执行循环体，否则不执行循环
 * 3，循环体：循环逻辑的处理
 * 4，迭代语句：修改变量的值，能够在某个时刻让循环条件不为true，从而结束循环，不为死循环
 */
public class While {
    public static void main(String[] args) {
        int i = 1;
        while (i <= 100) {
            System.out.println(i);
            i++;
        }

    }
}
