package com.kola.interface_auto.lesson45.pojo;

// 第二个sheet的信息
public class ApiDetail {
    //    caseId	apiId	isExcute	requestParams	expectedResponseData
    private String caseId;
    private String apiId;
    private String requestParams;
    private String expectedResponseData;

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

    public ApiDetail(String caseId, String apiId,  String requestParams, String expectedResponseData) {
        this.caseId = caseId;
        this.apiId = apiId;
        this.requestParams = requestParams;
        this.expectedResponseData = expectedResponseData;
    }

    public ApiDetail() {
    }
}
