package com.kola.java.lesson35;

import org.testng.annotations.Test;

// 分组测试（见groups.xml)
public class GroupTest {
    @Test(groups = {"g1"})
    public void a() {
        System.out.println("a");
    }

    @Test(groups = {"g2"})
    public void b() {
        System.out.println("b");
    }

    @Test(groups = {"g1"})
    public void c() {
        System.out.println("c");
    }
}
