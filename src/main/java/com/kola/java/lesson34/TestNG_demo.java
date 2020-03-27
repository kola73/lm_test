package com.kola.java.lesson34;

import org.testng.annotations.*;

/*
执行顺序：
beforeSuite
beforeTest
beforeClss
beforeMethod
test
afterMethod
afterClass
afterTest
afterSuite
作用：初始化数据，预处理，重置，释放资源
*/
public class TestNG_demo {
    // 在测试类之前
    @BeforeClass
    public void beforeClass() {
        System.out.println("beforeClss");
    }

    //在测试类之后
    @AfterClass
    public void afterClass() {
        System.out.println("afterClass");
    }

    //在方法之前
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("beforeMethod");
    }

    //在方法之后
    @AfterMethod
    public void afterMethod() {
        System.out.println("afterMethod");
    }

    // 单元测试方法
    @Test
    public void tests() {
        System.out.println("test");
    }

    //在测试套件前
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite");
    }

    //在测试套件后
    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite");
    }

    // 在某个测试所有方法执行前
    @BeforeTest
    public void beforeTest() {
        System.out.println("beforeTest");
    }

    // 在某个测试所有方法执行后
    @AfterTest
    public void afterTest() {
        System.out.println("afterTest");
    }
}
