package com.kola.interface_auto.lesson52.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/*
数据载体发生变化
1，以apiId作为key，url作为值 （hashmap）
2，以apiId作为key，requestMethod作为值（hashmap）
总而言之，给我一个apiId，我就能获取apiInfo的所有内容（ApiInfo类）
v4
静态代码块里，换了容器存放，所以之前的代码被替换，更加简化
 */
public class HttpUtils_v5 {

    private static String get(String url, Map<String, String> paramsMap) {
        String result = null;
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
        return result;

    }


    // 针对内容类型是原生表单post请求
    private static String post(String url, Map<String, String> mapParams) {
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
                // 创建原生form表单保存数据
                UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(listParams, "utf-8");
                // 设置请求体
                post.setEntity(encodedFormEntity);
                post.addHeader("cookie", "appliedwebsid=4da1beb3-431e-4b39-a536-f9db32158b01");
                post.addHeader("Content-Type", "application/json");
            }
            // 创建发包客户端
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            // 执行post请求
            HttpResponse response = closeableHttpClient.execute(post);
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

    // 针对内容类型json格式的post请求
    public static String post(String url, JSONObject json) {
        String result = null;
        CloseableHttpClient client = null;
        try {
            //创建Post对象
            HttpPost post = new HttpPost(url);
            // 设置请求头
            post.setHeader("Content-type", "application/json");
            post.setHeader("cookie", "appliedwebsid=4da1beb3-431e-4b39-a536-f9db32158b01");
            //给Post设置JSON格式的参数
            StringEntity entity = new StringEntity(json.toString(), "utf-8");
            entity.setContentEncoding("UTF-8");
            post.setEntity(entity);
            //创建发包客户端
            client = HttpClients.createDefault();
            //发送Post请求，获取返回值
            HttpResponse response = client.execute(post);
            // 获得响应体
            HttpEntity entity1 = response.getEntity();
            String entities = EntityUtils.toString(entity1);
            System.out.println("获得响应体：" + entities);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return result;
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
//        String url = "http://hc-t1.yonghuivip.com/signin";
//        Map<String, String> params = new HashMap<>();
//        params.put("username", "admin");
//        params.put("password","123456a");
//        String result = post(url, params);
//        System.out.println(result);
        String url = "http://hc-t1.yonghuivip.com/app/api/sms-config-center/ordertypeconfig/list";
        JSONObject params = new JSONObject();
        params.put("skucode", "3");
        System.out.println(params);
        String result = post(url, params);
        System.out.println(result);

    }

}
