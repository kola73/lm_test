package com.kola.interface_auto.lesson47.base;

import com.kola.interface_auto.lesson47.Utils.ExcelUtils_v7;
import org.testng.annotations.AfterSuite;

public class BaseTest {
    @AfterSuite
    public void afterSuite() {
        ExcelUtils_v7.batchWrite("api_v5.xlsx","target/classes/api_v5.xlsx",2);
    }
}
