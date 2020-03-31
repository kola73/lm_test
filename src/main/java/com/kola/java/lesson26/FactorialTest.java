package com.kola.java.lesson26;

/*
阶乘：
!5=5*4*3*2*1
!4=4*3*2*1  !4=4*!3  n=n*n-1
!3=3*2*1   !3=3*!2
!2=2*1   !2=2*!1
!1=1    ！1=1
!0=1    !0=1
 */
public class FactorialTest {
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            int i = n * factorial(n - 1);
            return i;
        }
    }

    public static void main(String[] args) {
        System.out.println(factorial(3));
    }

}
