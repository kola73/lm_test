package com.kola.interface_auto.lesson44;

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
import java.util.*;

/*
数据载体发生变化
1，以apiId作为key，url作为值 （hashmap）
2，以apiId作为key，requestMethod作为值（hashmap）
总而言之，给我一个apiId，我就能获取apiInfo的所有内容（ApiInfo类）

 */
public class HttpUtils_v3 {
    // 存放apiInfo信息的容器
    static Map<String, ApiInfo> apiInfoMap = new HashMap<>();

    // 静态代码块（只执行一次）
    static {
        Object[][] apiInfoMapDatas = ExcelUtils_v4.readExcel("/api_v4.xlsx", 1);
        // 遍历出每一行数据（每一行都是一个数组）
        for (Object[] apiInfoArray : apiInfoMapDatas) {
            ApiInfo apiInfo = new ApiInfo();
            // 第一个是apiId
            String apiId = apiInfoArray[0].toString();
            apiInfo.setApiId(apiId);
            // 第二个是apiName
            apiInfo.setApiName(apiInfoArray[1].toString());
            // 第三个是requestMethod
            apiInfo.setRequestMethod(apiInfoArray[2].toString());
            // 第四个是url
            apiInfo.setUrl(apiInfoArray[3].toString());
            // 放到apiInfo容器中
            apiInfoMap.put(apiId, apiInfo);
        }

    }

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

    // 通过apiId找到请求方法
    public static String getMethodsByApiId(String apiId) {
        return apiInfoMap.get(apiId).getRequestMethod();
    }

    // 通过apiId找到url
    public static String getUrlByApiId(String apiId) {
        return apiInfoMap.get(apiId).getUrl();
    }

    // 发包（分发各种请求）
    public static String request(String apiId, String url, Map<String, String> params) {
        String method = getMethodsByApiId(apiId);
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
        System.out.println(apiInfoMap.get("1").getRequestMethod());
    }

}
