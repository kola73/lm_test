package com.kola.interface_auto.lesson39;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/*
1，指定请求方法
2，准备url
3，准备参数
4，发包
5，查看响应结果
 */
public class PostRequest1 {

    @Test
    public void post() throws Exception {
        // 准备url
        String url = "http://hc-t1.yonghuivip.com/app/api/sms-config-center/ordertypeconfig" +
                "/list?pageindex=1&pagesize=50&timestamp=1587374227944&sign=ae6aecfed5cb7f8ed04c841740c0bbd7";
        // 指定请求方法
        HttpPost post = new HttpPost(url);
        // 如果有需要添加请求头，可以使用如下方法添加
        post.addHeader("cookie", "appliedwebsid=4da1beb3-431e-4b39-a536-f9db32158b01");
        post.addHeader("Content-Type","application/json");
        // 创建一个容器，保存每个参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("skucode", "3"));
        // 准备原生的form表单
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        // 把值存进表单
        post.setEntity(entity);
        // 创建发包客户端
        CloseableHttpClient client = HttpClients.createDefault();
        // 发送请求
        HttpResponse response = client.execute(post);
        // 得到响应头
        Header[] headers = response.getAllHeaders();
        System.out.println("获得响应头：");
        for (Header allHeader : headers) {
            System.out.println(allHeader.getName() + ":" + allHeader.getValue());
        }
        // 获得响应体
        HttpEntity entity1 = response.getEntity();
        String entities = EntityUtils.toString(entity1);
        System.out.println("获得响应体：" + entities);
        // 获得响应行
        System.out.println("获得响应行");
        StatusLine statusLine = response.getStatusLine();
        System.out.println(statusLine.getProtocolVersion());
        System.out.println(statusLine.getReasonPhrase());
        System.out.println(statusLine.getStatusCode());
//        Assert.assertEquals(statusLine.getStatusCode(), 302);
    }

}
