package com.kola.interface_auto.lesson52.pojo;

import java.util.LinkedHashMap;
import java.util.List;

// 描述前置或后置验证结果的pojo类

public class SQLChecker {
    private String no;
    private String sql;
    private List<LinkedHashMap<String, String>> expectedResult;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<LinkedHashMap<String, String>> getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(List<LinkedHashMap<String, String>> expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Override
    public String toString() {
        return "SQLChecker{" +
                "no='" + no + '\'' +
                ", sql='" + sql + '\'' +
                ", expectedResult=" + expectedResult +
                '}';
    }

    public SQLChecker(String no, String sql, List<LinkedHashMap<String, String>> expectedResult) {
        this.no = no;
        this.sql = sql;
        this.expectedResult = expectedResult;
    }

    public SQLChecker() {
    }
}
