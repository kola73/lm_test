package com.kola.interface_auto.lesson42;

import com.kola.interface_auto.lesson41.HttpUtils_v2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestCase {
    @DataProvider
    public Object[][] datas() {
        Object[][] datas = {
                {"admin", "123456a"},
                {"admin", null}
        };
        return datas;
    }

    @Test(dataProvider = "datas")
    public void testPost(String username, String password) {
        String url = "http://hc-t1.yonghuivip.com/signin";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        HttpUtils_v2.post(url, params);
    }

}
