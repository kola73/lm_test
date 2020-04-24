package com.kola.interface_auto.test.lesson53.pojo;

/**
 * 提取json串中的关键信息，做关键信息验证，断言
 *
 */

public class KeyInfo {
    private String jsonPath;

    private String value;

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
