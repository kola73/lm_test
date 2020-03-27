package com.kola.java.lesson34;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterSuccessTest {
    @Test
    public void registerSuccess(){
        System.out.println("注册成功");
        Assert.fail();
    }
}
