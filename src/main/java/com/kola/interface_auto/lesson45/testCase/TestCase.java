package com.kola.interface_auto.lesson45.testCase;

import com.alibaba.fastjson.JSONObject;
import com.kola.interface_auto.lesson45.Utils.ExcelUtils_v5;
import com.kola.interface_auto.lesson45.Utils.HttpUtils_v4;
import com.kola.interface_auto.lesson45.pojo.ApiDetail;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/*
优化点：
1，url应该从excel的第一个sheet里读取，不应该写死，用apiId进行关联
2，传的请求类型不能写死，要参数化
 */
public class TestCase {
    // 封装成一个列表，然后重新解析为二维数组
    @DataProvider
    public Object[][] datas() {
        List<Object> objLists = ExcelUtils_v5.readExcel("/api_v4.xlsx", 2, ApiDetail.class);
        // 获得list长度
        int listSize = objLists.size();
        // 创建一个容器（数据提供者需要的二维数组）--只要获得需要的信息即可
        // 底下需要的参数是4个，所以列长是4
        Object[][] data = new Object[listSize][4];
        for (int i = 0; i < listSize; i++) {
            ApiDetail apiDetail = (ApiDetail) objLists.get(i);
            // 测试数据需要啥数据就写啥（见下面）
            data[i][0] = apiDetail.getCaseId();
            data[i][1] = apiDetail.getApiId();
            data[i][2] = apiDetail.getRequestParams();
            data[i][3] = apiDetail.getExpectedResponseData();
        }

        return data;
    }

    @Test(dataProvider = "datas")
    public void testPost(String caseId,String apiId, String requestParams, String expectedResponseData) {
        // 准备url
        String url = HttpUtils_v4.getUrlByApiId(apiId);
        // 准备参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(requestParams);
        // 发包
        String results = HttpUtils_v4.request(apiId, url, params);
        System.out.println(results);

    }

}
