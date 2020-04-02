package com.kola.interface_auto.lesson44.test;

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
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpUtils {
    static Map<String, ApiInfo> maps = new HashMap<>();

    static {
        Object[][] excelParams = ExcelUtils.readExcel("/api_v4.xlsx", 1);
        for (Object[] paramsArray : excelParams) {
            ApiInfo apiInfo = new ApiInfo();
            String apiId = paramsArray[0].toString();
            apiInfo.setApiId(apiId);
            apiInfo.setApiName(paramsArray[1].toString());
            apiInfo.setRequestMethod(paramsArray[2].toString());
            apiInfo.setUrl(paramsArray[3].toString());
            maps.put(apiId, apiInfo);
        }
    }

    public static void post(String url, Map<String, String> mapParams) {
        // 创建post请求
        HttpPost post = new HttpPost(url);
        // 创建原生form表单，保存参数
        List<NameValuePair> paramsList = null;
        try {
            if (mapParams != null) {
                paramsList = new ArrayList<>();
                Set<String> key = mapParams.keySet();
                for (String keys : key) {
                    String values = mapParams.get(keys);
                    paramsList.add(new BasicNameValuePair(keys, values));
                }
                UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(paramsList);
                post.setEntity(encodedFormEntity);
            }
            // 创建发包客户端
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            // 发送请求
            HttpResponse response = closeableHttpClient.execute(post);
            // 得到请求体
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void get(String url, Map<String, String> mapParams) {
        // 准备好参数
        List<NameValuePair> listParams = null;
        try {
            if (mapParams != null) {
                listParams = new ArrayList<>();
                Set<String> key = mapParams.keySet();
                for (String keys : key) {
                    String value = mapParams.get(keys);
                    listParams.add(new BasicNameValuePair(keys, value));
                }
                // 准备好url
                String encodeUrl = URLEncodedUtils.format(listParams, "utf-8");
                url += "?" + encodeUrl;
            }
            // 创建get请求
            HttpGet get = new HttpGet(url);
            // 创建发包客户端
            CloseableHttpClient client = HttpClients.createDefault();
            // 发送请求
            HttpResponse response = client.execute(get);
            // 得到响应结果
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUrlByApiId(String apiId) {
        return maps.get(apiId).getUrl();
    }

    public static String getRequestByApiId(String apiId) {
        return maps.get(apiId).getRequestMethod();
    }

    public static String request(String apiId, String url, Map<String, String> params) {
        String methods = getRequestByApiId(apiId);
        String result = "";
        if ("get".equalsIgnoreCase(methods)) {
            get(url, params);
        } else if ("post".equalsIgnoreCase(methods)) {
            post(url, params);
        }
        return result;
    }
}
