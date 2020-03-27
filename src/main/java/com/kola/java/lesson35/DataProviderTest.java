package com.kola.java.lesson35;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
    @Test(dataProvider = "dp")
    public void test(int id, String name) {
        System.out.println(id + "-" + name);
    }

    @DataProvider
    public Object[][] dp() {
        return new Object[][]{
                {1, "kola"},
                {2, "lucy"},
                {3, "lily"},
        };
    }
}