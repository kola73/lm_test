package com.kola.interface_auto.lesson43;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtils_v2 {
    public static void get(String url, Map<String, String> paramsMap) {
        try {
            // 创建list存放名值对参数（为了排除耦合性，用了hashmap进行存储）
            List<NameValuePair> paramsList = null;
            // 加一个判断，防止报空指针（有参数设置参数，没有参数就不要赋值，设置为空）
            if (paramsMap != null) {
                paramsList = new ArrayList<>();
                Set<String> keys = paramsMap.keySet();
                for (String key : keys) {
                    String value = paramsMap.get(key);
                    paramsList.add(new BasicNameValuePair(key, value));
                }
                // 创建get请求
                String encodeParams = URLEncodedUtils.format(paramsList, "utf-8");
                url += ("?" + encodeParams);
            }
            HttpGet get = new HttpGet(url);
            //创建一个发包客户端
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            // 得到响应
            HttpResponse response = closeableHttpClient.execute(get);
            // 得到响应体
            System.out.println("获得响应体");
            HttpEntity entity = response.getEntity();
            String entities = EntityUtils.toString(entity);
            System.out.println(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void post(String url, Map<String, String> mapParams) {
        try {
            // 创建post请求
            HttpPost post = new HttpPost(url);
            // 创建list存放名值对参数（为了排除耦合性，用了hashmap进行存储）
            List<NameValuePair> listParams = null;
            // 加一个判断，防止报空指针（有参数设置参数，没有参数就不要赋值，设置为空）
            if (mapParams != null) {
                listParams = new ArrayList<>();
                Set<String> key = mapParams.keySet();
                for (String keys : key) {
                    String value = mapParams.get(keys);
                    listParams.add(new BasicNameValuePair(keys, value));
                }
                // 创建原生form表单保存数据
                UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(listParams);
                // 设置请求体
                post.setEntity(encodedFormEntity);
            }
            // 创建发包客户端
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            // 执行post请求
            HttpResponse response = closeableHttpClient.execute(post);
            // 获得响应体
            HttpEntity entity1 = response.getEntity();
            String entities = EntityUtils.toString(entity1);
            System.out.println("获得响应体：" + entities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
