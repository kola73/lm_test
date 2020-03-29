package com.kola.interface_auto.lesson40;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/*
存在问题
1，url，params硬编码
2，名值对不应该出现在测试用例里，这个是httpclient的库，如果有一天它修改了或者是要替换成其他接口测试的库，会出现问题--用hashmap
 */

public class TestCase {
    @Test
    public void getTest() {
        // 准备url
        String url = "http://hc-t1.yonghuivip.com/app/api/sms-hub/additionorder/additionlist?";
        Map<String, String> params = new HashMap<>();
        params.put("pagesize", "10");
        params.put("pageindex", "1");
        HttpUtils_v1.get(url, params);
    }

    @Test(dataProvider = "datas")
    public void postTest(String usermame,String password) {
        String url = "http://hc-t1.yonghuivip.com/signin";
        Map<String, String> param = new HashMap<>();
        param.put("username", usermame);
        param.put("password", password);
        HttpUtils_v1.post(url, param);
    }
}
