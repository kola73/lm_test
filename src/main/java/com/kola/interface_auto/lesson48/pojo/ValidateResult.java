package com.kola.interface_auto.lesson48.pojo;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 保存数据库验证结果的pojo类
 * [
 * {"no":"1","expectedResult":[{},{}],"result":"success"},
 * {"no":"2","expectedResult":[{},{}],"result":"fail"},
 * {"no":"3","expectedResult":[{},{}],"result":"fail"}
 * ]
 */
public class ValidateResult {
    private String id;
    private List<LinkedHashMap<String, String>> expectedResult;
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LinkedHashMap<String, String>> getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(List<LinkedHashMap<String, String>> expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ValidateResult(String id, List<LinkedHashMap<String, String>> expectedResult, String result) {
        this.id = id;
        this.expectedResult = expectedResult;
        this.result = result;
    }

    public ValidateResult() {
    }

    @Override
    public String toString() {
        return "ValidateResult{" +
                "id='" + id + '\'' +
                ", expectedResult=" + expectedResult +
                ", result='" + result + '\'' +
                '}';
    }
}
