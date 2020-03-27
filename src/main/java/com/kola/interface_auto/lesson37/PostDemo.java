package com.kola.interface_auto.lesson37;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
public class PostDemo {
    @DataProvider
    public Object[][] postTest() {
        Object[][] paramters = {
                {"admin", "123456a"},
                {"admin", "123333"}
        };
        return paramters;
    }

    @Test(dataProvider = "postTest")
    public void post(String userName, String pwd) throws Exception {
        // 准备url
        String url = "http://hc-t1.yonghuivip.com/signin";
        // 指定请求方法
        HttpPost post = new HttpPost(url);
        // 创建一个容器，保存每个参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", userName));
        params.add(new BasicNameValuePair("password", pwd));
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
//        System.out.println("获得响应头：");
        for (Header allHeader : headers) {
//            System.out.println(allHeader.getName() + ":" + allHeader.getValue());
        }
        // 获得响应体
        HttpEntity entity1 = response.getEntity();
        String entities = EntityUtils.toString(entity1);
//        System.out.println("获得响应体：" + entities);
        // 获得响应行
//        System.out.println("获得响应行");
        StatusLine statusLine = response.getStatusLine();
//        System.out.println(statusLine.getProtocolVersion());
//        System.out.println(statusLine.getReasonPhrase());
//        System.out.println(statusLine.getStatusCode());
        Assert.assertEquals(statusLine.getStatusCode(), 302);
    }

}
