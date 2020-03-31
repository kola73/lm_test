package com.kola.interface_auto.lesson42.Test;

import org.apache.http.HttpEntity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    public static void get(String url, Map<String, String> mapParams) {
        List<BasicNameValuePair> params = null;
        try {
            params = new ArrayList<>();
            if (mapParams != null) {
                Set<String> param = mapParams.keySet();
                for (String parameter : param) {
                    String value = mapParams.get(parameter);
                    params.add(new BasicNameValuePair(parameter, value));
                }
                String encodeUrl = URLEncodedUtils.format(params, "utf-8");
                url += "?" + encodeUrl;
            }
            HttpGet get = new HttpGet(url);
            CloseableHttpClient clients = HttpClients.createDefault();
            CloseableHttpResponse response = clients.execute(get);
            HttpEntity entity = response.getEntity();
            String entities = EntityUtils.toString(entity);
            System.out.println(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void post(String url, Map<String, String> params) {
        List<NameValuePair> paramsList = null;
        HttpPost post = new HttpPost(url);
        try {
            paramsList = new ArrayList<>();
            Set<String> key = params.keySet();
            for (String keys : key) {
                String value = params.get(keys);
                paramsList.add(new BasicNameValuePair(keys, value));
            }
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(paramsList);
            post.setEntity(encodedFormEntity);
            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            CloseableHttpResponse response = closeableHttpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String entities = EntityUtils.toString(entity);
            System.out.println(entities);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
