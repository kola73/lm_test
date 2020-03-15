package com.kola.java.lesson25;

import org.apache.log4j.Logger;

/*
异常：
try，catch，finally，throw，throws
 */
public class ExceptionTest {
    private static Logger logger = Logger.getLogger("ExceptionTest.class");

    public static void main(String[] args) {
        int a = 3;
        int b = 0;
        logger.info("运算开始");
        try {
            int result = a / b;
        } catch (ArithmeticException e) {
            logger.error("捕获到异常");
            e.printStackTrace();
        }
        logger.info("运算结束");
    }
}
