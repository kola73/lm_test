package com.kola.interface_auto.lesson45.test.utils;


import com.kola.interface_auto.lesson45.pojo.ApiInfo;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
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
    static Map<String, ApiInfo> mapParams = new HashMap<>();

    static {
        List<Object> objectList = ExcelUtils.readExcel("/api_v4.xlsx", 1, ApiInfo.class);
        for (Object objLists : objectList) {
            ApiInfo apiInfo = (ApiInfo) objLists;
            mapParams.put(apiInfo.getApiId(), apiInfo);
        }
    }

    public static void get(String url, Map<String, String> mapParams) {
        try {
            if (mapParams != null) {
                List<NameValuePair> lists = new ArrayList<>();
                Set<String> keys = mapParams.keySet();
                for (String key : keys) {
                    String value = mapParams.get(key);
                    lists.add(new BasicNameValuePair(key, value));
                }
                String encodeUrl = URLEncodedUtils.format(lists, "utf-8");
                url += "?" + encodeUrl;
            }
            HttpGet get = new HttpGet(url);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(get);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void post(String url, Map<String, String> mapParams) {
        List<NameValuePair> lists = null;
        try {
            if (mapParams != null) {
                lists = new ArrayList<>();
                Set<String> key = mapParams.keySet();
                for (String keys : key) {
                    String value = mapParams.get(keys);
                    lists.add(new BasicNameValuePair(keys, value));
                }
            }
            HttpPost post = new HttpPost(url);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(lists);
            post.setEntity(entity);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getUrlByApiId(String apiId) {
        return mapParams.get(apiId).getUrl();
    }

    public static String getMethodByApiId(String apiId) {
        return mapParams.get(apiId).getRequestMethod();
    }

    public static String request(String apiId, String url, Map<String, String> mapParams) {
        String result = "";
        String method = getMethodByApiId(apiId);
        if ("post".equalsIgnoreCase(method)) {
            post(url, mapParams);
        } else if ("get".equalsIgnoreCase(method)) {
            get(url, mapParams);
        }
        return result;
    }

}
