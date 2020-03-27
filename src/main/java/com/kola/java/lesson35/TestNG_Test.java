package com.kola.java.lesson35;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.annotations.Test;

public class TestNG_Test {
//    @Test(enabled = false)
    public void ignoreTest() {
        System.out.println("忽略测试");
    }

    //    @Test(timeOut = 1000)
    public void timeOutTest() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("超时测试");
    }

    // 异常测试（支持多个异常）
//    @Test(expectedExceptions = {NullPointerException.class,ClassNotFoundException.class})
    public void exceptionTest() {
        String str = null;
        System.out.println(str.length());
    }

    // 依赖测试
    @Test
    public void a() {
        System.out.println("a");
    }

    @Test(dependsOnMethods = {"c"})
    public void b() {
        System.out.println("b");
    }

    @Test
    public void c() {
        System.out.println("c");
    }
}
