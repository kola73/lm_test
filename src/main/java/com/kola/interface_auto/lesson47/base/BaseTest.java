package com.kola.interface_auto.lesson47.base;

import com.kola.interface_auto.lesson47.Utils.ExcelUtils_v7;
import org.testng.annotations.AfterSuite;

// 结束后一次性把所有信息写入目标文件

public class BaseTest {
    @AfterSuite
    public void afterSuite() {
        ExcelUtils_v7.batchWrite("api_v4.xlsx", "target/classes/api_v4.xlsx", 2);
    }
}
