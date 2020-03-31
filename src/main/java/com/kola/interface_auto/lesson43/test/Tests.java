package com.kola.interface_auto.lesson43.test;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class Tests {
    @DataProvider
    public Object[][] datas() {
        Object[][] datas = ExcelUtils.readExcel("/api_v4.xlsx", 2);
        return datas;
    }

    @Test(dataProvider = "datas")
    public void test(String apiId, String requestParams, String expectedResponseData) {
        String url = "http://hc-t1.yonghuivip.com/signin";
        Map<String, String> data = (Map<String, String>) JSONObject.parse(requestParams);
        HttpUtils.post(url, data);
    }
}
