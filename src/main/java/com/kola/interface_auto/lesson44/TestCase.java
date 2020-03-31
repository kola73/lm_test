package com.kola.interface_auto.lesson44;

import com.alibaba.fastjson.JSONObject;
import com.kola.interface_auto.lesson43.ExcelUtils_v4;
import com.kola.interface_auto.lesson43.HttpUtils_v2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/*
优化点：
1，url应该从excel的第一个sheet里读取，不应该写死，用apiId进行关联
2，传的请求类型不能写死，要参数化
 */
public class TestCase {
    @DataProvider
    public Object[][] datas() {
        Object[][] datas = ExcelUtils_v4.readExcel("/api_v4.xlsx", 2);
        return datas;
    }

    @Test(dataProvider = "datas")
    public void testPost(String apiId, String requestParams, String expectedResponseData) {
        // 准备url
        String url = HttpUtils_v3.getUrlByApiId(apiId);
        // 准备参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(requestParams);
        // 发包
        String result = HttpUtils_v3.request(apiId, url, params);
        System.out.println(result);

    }

}
