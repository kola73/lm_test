package com.kola.interface_auto.lesson43.test;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    public static void get(String url, Map<String, String> paramsMap) {
        try {
            List<NameValuePair> params = null;
            if (paramsMap != null) {
                params = new ArrayList<>();
                Set<String> key = paramsMap.keySet();
                for (String keys : key) {
                    String value = paramsMap.get(keys);
                    params.add(new BasicNameValuePair(keys, value));
                    String encodeParams = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + encodeParams;
                }
            }
            HttpGet get = new HttpGet(url);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(get);
            String entity = EntityUtils.toString(response.getEntity());
            System.out.println(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void post(String url, Map<String, String> paramsMap) {
        List<NameValuePair> params = null;
        HttpPost post = new HttpPost(url);
        try {
            if (paramsMap != null) {
                params = new ArrayList<>();
                Set<String> key = paramsMap.keySet();
                for (String keys : key) {
                    String value = paramsMap.get(keys);
                    params.add(new BasicNameValuePair(keys, value));
                }
            }
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params);
            post.setEntity(encodedFormEntity);
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
}
