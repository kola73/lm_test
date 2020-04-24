package com.kola.interface_auto.test.lesson53.testCase;

import com.alibaba.fastjson.JSONObject;
import com.kola.interface_auto.test.lesson53.Utils.ApiUtils;
import com.kola.interface_auto.test.lesson53.Utils.DataValidateUtils;
import com.kola.interface_auto.test.lesson53.Utils.ExcelUtils_v8;
import com.kola.interface_auto.test.lesson53.Utils.HttpUtils_v6;
import com.kola.interface_auto.test.lesson53.base.BaseTest_v2;
import com.kola.interface_auto.test.lesson53.pojo.ApiDetail;
import com.kola.interface_auto.test.lesson53.pojo.CellData;
import com.kola.interface_auto.test.lesson53.pojo.ExcelObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
/*
鉴权：
鉴定请求是否合法
如果合法，正常处理。比如测试审核接口的时候，如果不合法，提示：请先登录
常用鉴权方式：
1，id+status: x
id+status+phone+pwd √
缺点：不方便，存在安全隐患
2，token令牌
服务器签发给客户端，当用户登录成功（set-cookie:xxxxxxxxxxx)
客户端把令牌保存起来：文件/内存/数据库
下一次访问时，带上这个token
缺点：服务器对token维护成本非常高
3，对称加密，非对称加密（非对称加密判断客户端是否合法，然后以对称加密去传输后期数据，然后客户端可以进行加解密）
接口中常用到非对称加密，它会生成公钥（加密：客户端）和私钥（解密：服务器），
 */

/*
优化点：
1，url应该从excel的第一个sheet里读取，不应该写死，用apiId进行关联
2，传的请求类型不能写死，要参数化
v6：新增响应数据回写
 */

// 这里记得要继承baseTest，否则会报错
public class TestCase extends BaseTest_v2 {
    // 封装成一个列表，然后重新解析为二维数组
    @DataProvider
    public Object[][] datas() {
        List<? extends ExcelObject> objLists = ExcelUtils_v8.readExcel("/api_v9.xlsx", 2, ApiDetail.class);
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
    public void testPost(String caseIad, String apiId, String requestPrams, String expectedResponseData,
                         String preCheckSQL, String afterCheckSQL) {
        // 前置验证（cellNum写回到excel的第七列，所以输入7，后置的同上）
        DataValidateUtils.preValidate(caseIad, 7, preCheckSQL);
        // 准备url
        String url = ApiUtils.getUrlByApiId(apiId);
        // 准备参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(requestPrams);
        // 发包
        String results = HttpUtils_v6.request(apiId, url, params);
        ExcelUtils_v8.addCellData(new CellData(caseIad, 5, results));
        // 后置验证
        DataValidateUtils.afterValidate(caseIad, 9, afterCheckSQL);
    }


}
