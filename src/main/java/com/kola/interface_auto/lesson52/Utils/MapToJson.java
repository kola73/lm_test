package com.kola.interface_auto.lesson52.Utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class MapToJson {
    private static void addSessionIdToGlobalData(HttpResponse response) {
        // 获得第一个header（因为cookie通常都是放第一个，所以我们直接取了）
        Header header = response.getFirstHeader("Set-Cookie");
        if (header != null) {
            // 获得Set-Cookie的第一个值
            String headerValue = header.getValue();
            // 判断是否有值 trim()去除前后空格
            if (headerValue != null && headerValue.trim().length() > 0) {
                // 完整的cookie：appliedwebsid=c1625c00-4254-4582-a5e9-8a5d2c18573a; Path=/; Max-Age=86400; Expires=Tue, 21-Apr-2020 15:10:57 GMT; HttpOnly
                if (headerValue.contains("appliedwebsid")) {
                    // 截串，获取到我们需要的cookie
                    int idx = headerValue.indexOf(";");
                    // 截取到我们需要的 “appliedwebsid=c1625c00-4254-4582-a5e9-8a5d2c18573a”
                    String appliedwebsid = headerValue.substring(0, idx);
                    // 添加到全局变量
                    ParamsUtils.addGlobalData("appliedwebsid", appliedwebsid);
                }

            }
        }
    }

    private static String post(String url, Map<String, String> mapParams, String entityType) {
        String result = null;
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
                if (entityType == "urlEncodedFormEntity"){
                    // 创建原生form表单保存数据
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(listParams, "utf-8");
                    // 设置请求体
                    post.setEntity(entity);
                } else if (entityType == "Json") {
                    //给Post设置JSON格式的参数
                    StringEntity entity = new StringEntity(mapParams.toString(), "utf-8");
                    // 设置请求体
                    post.setEntity(entity);
                }
                // 添加cookie请求头
                String appliedwebsid = ParamsUtils.getGlobalValue("appliedwebsid");
                if (appliedwebsid != null) {
                    post.addHeader("cookie", appliedwebsid);
                }
//                post.addHeader("Content-Type", "application/json");
            }
            // 创建发包客户端
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            // 执行post请求
            HttpResponse response = closeableHttpClient.execute(post);
            // 将sessionId添加到全局数据池
            addSessionIdToGlobalData(response);
            System.out.println(response);
            // 获得响应体
            HttpEntity entity1 = response.getEntity();
            String entities = EntityUtils.toString(entity1);
            System.out.println("获得响应体：" + entities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String url = "http://hc-t1.yonghuivip.com/signin";
        Map<String, String> params = new HashMap<>();
        params.put("username", "admin");
        params.put("password", "123456a");
        String result = post(url, params,"urlEncodedFormEntity");
        System.out.println(result);
    }
}