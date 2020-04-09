package com.kola.interface_auto.MyTest.base;

import com.kola.interface_auto.MyTest.utils.ExcelUtils;
import org.testng.annotations.AfterSuite;

public class BaseCase {
    @AfterSuite
    public void afterSuite(){
        ExcelUtils.batchWrite("/api_v4.xlsx","/api_v4.xlsx",2);
    }
}
