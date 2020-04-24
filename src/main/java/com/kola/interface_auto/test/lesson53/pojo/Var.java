package com.kola.interface_auto.test.lesson53.pojo;

/**
 * 参数化--》接口关联，提取之前请求的响应结果数据提供下个接口的鉴权
 *
 */
public class Var {
    private String jsonPath;

    private String key;//-->保存到全局数据池中的key

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
