package com.kola.interface_auto.lesson44.test;

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
    public void tests(String apiId, String requestParams, String expectedResponse) {
        Map<String, String> params = (Map<String, String>) JSONObject.parse(requestParams);
        String url = HttpUtils.getUrlByApiId(apiId);
        String results = HttpUtils.request(apiId, url, params);
        System.out.println(results);

    }
}
