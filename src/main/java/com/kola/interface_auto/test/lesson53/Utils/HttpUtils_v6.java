package com.kola.interface_auto.test.lesson53.Utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
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
注意： 如果遇到响应结果是302的，我们要提取header里面的location进行重定向
数据载体发生变化
1，以apiId作为key，url作为值 （hashmap）
2，以apiId作为key，requestMethod作为值（hashmap）
总而言之，给我一个apiId，我就能获取apiInfo的所有内容（ApiInfo类）
v4
静态代码块里，换了容器存放，所以之前的代码被替换，更加简化
 */
public class HttpUtils_v6 {

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
            // 添加cookie请求头
            String appliedwebsid = ParamsUtils.getGlobalValue("appliedwebsid");
            if (appliedwebsid != null) {
                get.addHeader("cookie", appliedwebsid);
            }
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
                // 添加cookie请求头
                String appliedwebsid = com.kola.interface_auto.lesson52.Utils.ParamsUtils.getGlobalValue("appliedwebsid");
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

    // 针对内容类型json格式的post请求(这里要注意，把这个想办法换成Map
    public static String post(String url, JSONObject json) {
        String result = null;
        CloseableHttpClient client = null;
        try {
            //创建Post对象
            HttpPost post = new HttpPost(url);
            // 设置请求头
            post.addHeader("Content-type", "application/json");
//            post.addHeader("cookie", "appliedwebsid=4da1beb3-431e-4b39-a536-f9db32158b01");
            // 添加cookie请求头
            String appliedwebsid = ParamsUtils.getGlobalValue("appliedwebsid");
            if (appliedwebsid != null) {
                post.addHeader("cookie", ParamsUtils.getGlobalValue("appliedwebsid"));
            }
            //给Post设置JSON格式的参数
            StringEntity entity = new StringEntity(json.toString(), "utf-8");
            entity.setContentEncoding("UTF-8");
            post.setEntity(entity);
            //创建发包客户端
            client = HttpClients.createDefault();
            //发送Post请求，获取返回值
            HttpResponse response = client.execute(post);
            // 将sessionId添加到全局数据池
            addSessionIdToGlobalData(response);
            System.out.println(response);
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

    /**
     * 将登陆成功返回的token保存到全局数据池中
     *
     * @param response
     */
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


    // 发包（分发各种请求）
    public static String request(String apiId, String url, Map<String, String> params) {
        String method = ApiUtils.getMethodsByApiId(apiId);
        String result = "";
        String entityType = ApiUtils.getEntityTypeByApiId(apiId);
        if ("get".equalsIgnoreCase(method)) {
            get(url, params);
        } else if ("post".equalsIgnoreCase(method)) {
            if ("Json".equalsIgnoreCase(entityType)) {
                JSONObject json = (JSONObject) JSONObject.toJSON(params);
                post(url, json);
            } else if (("urlEncodedFormEntity".equalsIgnoreCase(entityType))) {
                post(url, params);
            }

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
//        params.put("password", "123456a");
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
