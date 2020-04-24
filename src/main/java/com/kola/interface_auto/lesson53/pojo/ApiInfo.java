package com.kola.interface_auto.lesson53.pojo;

//ApiInfo sheet页信息（第一个sheet）

public class ApiInfo extends ExcelObject {
    private String apiId;
    private String apiName;
    private String requestMethod;
    private String url;
    private String entityType;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public ApiInfo() {
    }

    public ApiInfo(String apiId, String apiName, String requestMethod, String url, String entityType) {
        this.apiId = apiId;
        this.apiName = apiName;
        this.requestMethod = requestMethod;
        this.url = url;
        this.entityType = entityType;
    }

    @Override
    public String toString() {
        return "ApiInfo{" +
                "apiId='" + apiId + '\'' +
                ", apiName='" + apiName + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", url='" + url + '\'' +
                ", entityType='" + entityType + '\'' +
                '}';
    }
}
