package com.kola.java.lesson11;

/**
 * do..while和while的区别
 * 循环次数和判断次数的关系
 * while：先判断后执行，判断满足条件才会继续执行循环体，不满足结束循环
 * 判断次数=循环次数+1
 * 极端法：让条件永远不满足：判断次数为1，循环次数为0
 * do..while:先执行后判断，判断满足条件继续执行循环体，不满足结束循环
 * 判断次数=循环次数
 * 极端法：让条件永远不满足：判断次数为1，循环次数为1
 */
public class DoWhile {
    public static void main(String[] args) {
        int i = 1;
        do {
            System.out.println(i);
//            i++;
            i += 2;
        } while (i <= 100);
    }
}
