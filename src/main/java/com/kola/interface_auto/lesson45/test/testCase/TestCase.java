package com.kola.interface_auto.lesson45.test.testCase;

import com.alibaba.fastjson.JSONObject;
import com.kola.interface_auto.lesson45.test.pojo.ApiDetail;
import com.kola.interface_auto.lesson45.test.utils.ExcelUtils;
import com.kola.interface_auto.lesson45.test.utils.HttpUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestCase {
    @DataProvider
    public Object[][] datas() {
        List<Object> params = ExcelUtils.readExcel("/api_v4.xlsx", 2, ApiDetail.class);
        int listLength = params.size();
        Object[][] objParams = new Object[listLength][4];
        for (int i = 0; i < listLength; i++) {
            ApiDetail apiDetail = (ApiDetail) params.get(i);
            objParams[i][0] = apiDetail.getCaseId();
            objParams[i][1] = apiDetail.getApiId();
            objParams[i][2] = apiDetail.getRequestParams();
            objParams[i][3] = apiDetail.getExpectedResponseData();
        }
        return objParams;
    }

    @Test(dataProvider = "datas")
    public void test(String CaseId, String apiId, String requestParams, String expectedResponseData) {
        String url = HttpUtils.getUrlByApiId(apiId);
        Map<String, String> params = (Map<String, String>) JSONObject.parse(requestParams);
        String result = HttpUtils.request(apiId, url, params);
        System.out.println(result);

    }
}
