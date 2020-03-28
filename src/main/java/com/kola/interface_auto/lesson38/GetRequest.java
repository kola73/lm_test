package com.kola.interface_auto.lesson38;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GetRequest {
    @Test
    public void getTest() throws Exception {
        // 准备url
        String url = "http://hc-t1.yonghuivip.com/app/api/sms-hub/additionorder/additionlist?";
        // 为了防止参数出现乱码，所以这里加了编码格式
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("pagesize", "10"));
        params.add(new BasicNameValuePair("pageindex", "1"));
        String encodeParams = URLEncodedUtils.format(params, "utf-8");
        url += encodeParams;
        // 创建get请求
        HttpGet get = new HttpGet(url);
        //创建一个发包客户端
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        // 得到响应
        HttpResponse response = closeableHttpClient.execute(get);
        // 得到响应头
        Header[] headers = response.getAllHeaders();
        System.out.println("获得请求头");
        for (Header allHeader : headers) {
            System.out.println(allHeader.getName() + ":" + allHeader.getValue());
        }
        // 得到响应状态行
        System.out.println("获得响应状态行");
        StatusLine statusLine = response.getStatusLine();
        System.out.println(statusLine.getStatusCode());
        // 得到响应体
        System.out.println("获得响应体");
        HttpEntity entity = response.getEntity();
        String entities = EntityUtils.toString(entity);
        System.out.println(entities);
    }
}
