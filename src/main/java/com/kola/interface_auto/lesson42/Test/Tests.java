package com.kola.interface_auto.lesson42.Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Tests {
    @DataProvider
    public Object[][] datas() {
        Object[][] datas = {
                {"admin", "123456a"}
        };
        return datas;
    }


    @Test(dataProvider = "datas")
    public void test(String username, String password) {
        String url = "http://hc-t1.yonghuivip.com/signin";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        HttpUtils.post(url, params);
    }

    public static void main(String[] args) {
//        String url = "http://hc-t1.yonghuivip.com/app/api/sms-hub/additionorder/additionlist?";
//        Map<String, String> params = new HashMap<>();
//        params.put("pagesize", "123456a");
//        params.put("index", "1");
//        HttpUtils.get(url, params);
    }
}
