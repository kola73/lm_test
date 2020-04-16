package com.kola.interface_auto.lesson50.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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

/*
数据载体发生变化
1，以apiId作为key，url作为值 （hashmap）
2，以apiId作为key，requestMethod作为值（hashmap）
总而言之，给我一个apiId，我就能获取apiInfo的所有内容（ApiInfo类）
v4
静态代码块里，换了容器存放，所以之前的代码被替换，更加简化
 */
public class HttpUtils_v4 {

    private static void get(String url, Map<String, String> paramsMap) {
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


    private static void post(String url, Map<String, String> mapParams) {
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


    // 发包（分发各种请求）
    public static String request(String apiId, String url, Map<String, String> params) {
        String method = ApiUtils.getMethodsByApiId(apiId);
        String result = "";
        if ("get".equalsIgnoreCase(method)) {
            get(url, params);
        } else if ("post".equalsIgnoreCase(method)) {
            post(url, params);
        }
        return result;
    }

    public static void main(String[] args) {
//        Set<String> key = apiInfoMap.keySet();
//        for (String keys : key) {
//            System.out.println(keys + ":" + apiInfoMap.get(keys));
//        }
//        System.out.println(apiInfoMap.get("2").getRequestMethod());
    }

}
