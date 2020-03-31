package com.kola.interface_auto.lesson43;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class TestCase {
    @DataProvider
    public Object[][] datas() {
        Object[][] datas = ExcelUtils_v4.readExcel("/api_v4.xlsx", 2);
        return datas;
    }

    @Test(dataProvider = "datas")
    public void testPost( String apiId, String requestParams, String expectedResponseData) {
        String url = "http://hc-t1.yonghuivip.com/signin";
        Map<String, String> params = (Map<String, String>) JSONObject.parse(requestParams);
        HttpUtils_v2.post(url, (Map<String, String>) params);
    }

}
