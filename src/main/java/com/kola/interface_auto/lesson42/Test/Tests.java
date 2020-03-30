package com.kola.interface_auto.lesson42.Test;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String url = "http://hc-t1.yonghuivip.com/app/api/sms-hub/additionorder/additionlist?";
        Map<String, String> params = new HashMap<>();
        params.put("pagesize", "123456a");
        params.put("index","1");
        HttpUtils.get(url, params);
    }
}
