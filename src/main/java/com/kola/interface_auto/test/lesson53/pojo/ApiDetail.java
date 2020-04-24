package com.kola.interface_auto.test.lesson53.pojo;

// 第二个sheet的信息(新增实际响应结果字段）
public class ApiDetail extends ExcelObject {
    // CaseId,ApiId,RequestParams,ExpectedResponseData,ActualResponseData,PreCheckSQL,PreCheckResult,AfterCheckSQL,AfterCheckResult
    private String caseId;
    private String apiId;
    private String requestParams;
    private String expectedResponseData;
    private String ActualResponseData;
    private String preCheckSQL;
    private String preCheckResult;
    private String afterCheckSQL;
    private String afterCheckResult;
    private String preCheckValidate;
    private String afterCheckValidate;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getExpectedResponseData() {
        return expectedResponseData;
    }

    public void setExpectedResponseData(String expectedResponseData) {
        this.expectedResponseData = expectedResponseData;
    }

    public String getActualResponseData() {
        return ActualResponseData;
    }

    public void setActualResponseData(String actualResponseData) {
        ActualResponseData = actualResponseData;
    }

    public String getPreCheckSQL() {
        return preCheckSQL;
    }

    public void setPreCheckSQL(String preCheckSQL) {
        this.preCheckSQL = preCheckSQL;
    }

    public String getPreCheckResult() {
        return preCheckResult;
    }

    public void setPreCheckResult(String preCheckResult) {
        this.preCheckResult = preCheckResult;
    }

    public String getAfterCheckSQL() {
        return afterCheckSQL;
    }

    public void setAfterCheckSQL(String afterCheckSQL) {
        this.afterCheckSQL = afterCheckSQL;
    }

    public String getAfterCheckResult() {
        return afterCheckResult;
    }

    public void setAfterCheckResult(String afterCheckResult) {
        this.afterCheckResult = afterCheckResult;
    }

    public String getPreCheckValidate() {
        return preCheckValidate;
    }

    public void setPreCheckValidate(String preCheckValidate) {
        this.preCheckValidate = preCheckValidate;
    }

    public String getAfterCheckValidate() {
        return afterCheckValidate;
    }

    public void setAfterCheckValidate(String afterCheckValidate) {
        this.afterCheckValidate = afterCheckValidate;
    }

    public ApiDetail() {
    }

    public ApiDetail(String caseId, String apiId, String requestParams, String expectedResponseData, String actualResponseData, String preCheckSQL, String preCheckResult, String afterCheckSQL, String afterCheckResult, String preCheckValidate, String afterCheckValidate) {
        this.caseId = caseId;
        this.apiId = apiId;
        this.requestParams = requestParams;
        this.expectedResponseData = expectedResponseData;
        ActualResponseData = actualResponseData;
        this.preCheckSQL = preCheckSQL;
        this.preCheckResult = preCheckResult;
        this.afterCheckSQL = afterCheckSQL;
        this.afterCheckResult = afterCheckResult;
        this.preCheckValidate = preCheckValidate;
        this.afterCheckValidate = afterCheckValidate;
    }

    @Override
    public String toString() {
        return "ApiDetail{" +
                "caseId='" + caseId + '\'' +
                ", apiId='" + apiId + '\'' +
                ", requestParams='" + requestParams + '\'' +
                ", expectedResponseData='" + expectedResponseData + '\'' +
                ", ActualResponseData='" + ActualResponseData + '\'' +
                ", preCheckSQL='" + preCheckSQL + '\'' +
                ", preCheckResult='" + preCheckResult + '\'' +
                ", afterCheckSQL='" + afterCheckSQL + '\'' +
                ", afterCheckResult='" + afterCheckResult + '\'' +
                ", preCheckValidate='" + preCheckValidate + '\'' +
                ", afterCheckValidate='" + afterCheckValidate + '\'' +
                ",getRowNum='" + getRowNum() + '\'' +
                '}';
    }
}
