package main.java.com.kola.java.lesson25;

import org.apache.log4j.Logger;

/*
异常：
java的异常一般分为2类，异常（exception）和错误（error），都继承自父类throwable
exception表示程序可以处理的情况，一般是直接处理或者抛出
error一般指跟虚拟机有关的严重问题，比如线程死锁，内存溢出等，遇到这种问题，建议直接关闭
exception分为2类：
编译时异常（继承自exception）:找不到类ClassNotFoundException,找不到方法NoSuchmethodException，拒绝访问目标类IllegalAccessException
运行时异常（继承自runtimeexception）：空指针，数组下标越界，类型转换异常，算数异常
1,关键字：try，catch，finally，throw，throws
2，可以捕获多个异常，父类的异常要放到子类的后面，否则会报错
3，finally块每次都会执行，一般用于回收资源，比如数据库的连接释放等
4，throws抛出异常后，抛出的编译型异常会传给上一级调用者，上一级调用者必须处理，或抛出或捕获，最后由java虚拟机执行
5，throw一般用于自定义的异常
 */
public class ExceptionTest {
    private static Logger logger = Logger.getLogger("ExceptionTest.class");

    public void throwException() {
        throw new RuntimeException();
    }

    public void test() throws ClassNotFoundException {
        Class.forName("com.kola.demo");
    }

    // 自定义异常
    public class myException extends RuntimeException {
        public myException() {
            super("我的自定义异常");
        }

        // 调用自定义异常
        public void myExceptionTest() {
            throw new myException();
        }

    }

    public static void main(String[] args) {
        int a = 3;
        int b = 0;
        logger.info("运算开始");
        try {
            int result = a / b;
        } catch (ArithmeticException e) {
            logger.error("捕获到异常");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // 打印堆栈信息
            e.getMessage(); //获得详细的描述信息
            e.getCause(); // 打印发生异常的原因
        } finally {
            System.out.println("结束啦~");
        }
        logger.info("运算结束");
    }
}
