package com.kola.interface_auto.lesson48_49.testCase;

import com.alibaba.fastjson.JSONObject;
import com.kola.interface_auto.lesson48_49.Utils.ApiUtils;
import com.kola.interface_auto.lesson48_49.Utils.DataValidateUtils;
import com.kola.interface_auto.lesson48_49.Utils.ExcelUtils_v7;
import com.kola.interface_auto.lesson48_49.Utils.HttpUtils_v4;
import com.kola.interface_auto.lesson48_49.base.BaseTest;
import com.kola.interface_auto.lesson48_49.pojo.ApiDetail;
import com.kola.interface_auto.lesson48_49.pojo.CellData;
import com.kola.interface_auto.lesson48_49.pojo.ExcelObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/*
优化点：
1，url应该从excel的第一个sheet里读取，不应该写死，用apiId进行关联
2，传的请求类型不能写死，要参数化
v6：新增响应数据回写
 */

// 这里记得要继承baseTest，否则会报错
public class TestCase extends BaseTest {
    // 封装成一个列表，然后重新解析为二维数组
    @DataProvider
    public Object[][] datas() {
        List<? extends ExcelObject> objLists = ExcelUtils_v7.readExcel("/api_v5.xlsx", 2, ApiDetail.class);
        // 获得list长度
        int listSize = objLists.size();
        // 创建一个容器（数据提供者需要的二维数组）--只要获得需要的信息即可
        // 底下需要的参数是6个，所以列长是6
        Object[][] data = new Object[listSize][6];
        for (int i = 0; i < listSize; i++) {
            ApiDetail apiDetail = (ApiDetail) objLists.get(i);
            // 测试数据需要啥数据就写啥（见下面）
            data[i][0] = apiDetail.getCaseId();
            data[i][1] = apiDetail.getApiId();
            data[i][2] = apiDetail.getRequestParams();
            data[i][3] = apiDetail.getExpectedResponseData();
            data[i][4] = apiDetail.getPreCheckSQL();
            data[i][5] = apiDetail.getAfterCheckSQL();
        }

        return data;
    }

    @Test(dataProvider = "datas")
    public void testPost(String caseId, String apiId, String requestParams, String expectedResponseData,
                         String preCheckSQL, String afterCheckSQL) {
        // 前置验证（cellNum写回到excel的第七列，所以输入7，后置的同上）
        DataValidateUtils.preValidate(caseId, 7, preCheckSQL);
        // 准备url
        String url = ApiUtils.getUrlByApiId(apiId);
        // 准备参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(requestParams);
        // 发包
        String results = HttpUtils_v4.request(apiId, url, params);
//        System.out.println(results);
//        ExcelUtils_v7.writeExcel("target/classes/api_v5.xlsx", 2, caseId, 5, results);
        ExcelUtils_v7.addCellData(new CellData(caseId, 5, results));
        // 后置验证
        DataValidateUtils.afterValidate(caseId, 9, afterCheckSQL);
    }


}
