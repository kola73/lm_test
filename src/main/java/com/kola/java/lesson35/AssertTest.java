package com.kola.java.lesson35;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertTest {
    @Test
    public void assertTest() {
        Assert.assertEquals(1, 2);
        Assert.assertNotEquals(1, 2);
        Assert.assertTrue(1 == 1);
        Assert.assertFalse(1 == 3);
        String name = null;
        Assert.assertNotNull(name);
        Assert.assertNull(name);
    }
}
