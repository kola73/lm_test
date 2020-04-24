package com.kola.interface_auto.lesson52.base;

import com.kola.interface_auto.lesson52.Utils.ParamsUtils;
import com.kola.interface_auto.lesson52.Utils.ExcelUtils_v8;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest_v2 {
    // 在测试前先准备好数据
    @BeforeSuite
    public void beforeSuite() {
        // 数据准备，查询出一个用户名，然后做些修改（这里假设我们查出的是admin）
        ParamsUtils.addGlobalData("username","admin");

    }

    // 结束后一次性把所有信息写入目标文件
    @AfterSuite
    public void afterSuite() {
        ExcelUtils_v8.batchWrite("/api_v7.xlsx", "target/classes/api_v7.xlsx", 2);
    }
}
