package com.kola.interface_auto.lesson47.pojo;

// 第二个sheet的信息(新增实际响应结果字段）
public class ApiDetail extends ExcelObject {
    //    caseId	apiId	isExcute	requestParams	expectedResponseData
    private String caseId;
    private String apiId;
    private String requestParams;
    private String expectedResponseData;
    private String ActualResponseData;

    public String getActualResponseData() {
        return ActualResponseData;
    }

    public void setActualResponseData(String actualResponseData) {
        ActualResponseData = actualResponseData;
    }

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

    public ApiDetail() {
    }

    @Override
    public String toString() {
        return "ApiDetail{" +
                "caseId='" + caseId + '\'' +
                ", apiId='" + apiId + '\'' +
                ", requestParams='" + requestParams + '\'' +
                ", expectedResponseData='" + expectedResponseData + '\'' +
                ", ActualResponseData='" + ActualResponseData + '\'' +
                ",getRowNum='" + getRowNum() + '\'' +
                '}';
    }

}
