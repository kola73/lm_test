package com.kola.interface_auto.lesson48_49.base;

import com.kola.interface_auto.lesson48_49.Utils.ExcelUtils_v7;
import org.testng.annotations.AfterSuite;

// 结束后一次性把所有信息写入目标文件

public class BaseTest {
    @AfterSuite
    public void afterSuite() {
        ExcelUtils_v7.batchWrite("/api_v5.xlsx", "target/classes/api_v5.xlsx", 2);
    }
}
