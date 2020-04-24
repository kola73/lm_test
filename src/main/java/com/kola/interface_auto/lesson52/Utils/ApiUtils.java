package com.kola.interface_auto.lesson52.Utils;

import com.kola.interface_auto.lesson52.pojo.ApiDetail;
import com.kola.interface_auto.lesson52.pojo.ApiInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtils {
    // 存放apiInfo信息的容器
    static Map<String, ApiInfo> apiInfoMap = new HashMap<>();
    static Map<String, ApiDetail> apiDetailMap = new HashMap<>();

    // 静态代码块（只执行一次）
    // 这边也把类型换成了List<Object>
    static {
        // 拿到apiInfo的所有信息，
        List<ApiInfo> apiInfoList = (List<ApiInfo>) ExcelUtils_v8.readExcel("/api_v7.xlsx", 1, ApiInfo.class);
        for (ApiInfo apiInfo : apiInfoList) {
            apiInfoMap.put(apiInfo.getApiId(), apiInfo);
        }
        // 拿到apiDetail的所有信息
        List<ApiDetail> apiDetailList = (List<ApiDetail>) ExcelUtils_v8.readExcel("/api_v7.xlsx", 2, ApiDetail.class);
        for (ApiDetail apiDetail : apiDetailList) {
            apiDetailMap.put(apiDetail.getCaseId(), apiDetail);
        }

    }

    // 通过apiId找到请求方法
    public static String getMethodsByApiId(String apiId) {
        return apiInfoMap.get(apiId).getRequestMethod();
    }

    // 通过apiId找到url
    public static String getUrlByApiId(String apiId) {
        return apiInfoMap.get(apiId).getUrl();
    }

    // 通过caseId获得行号
    public static int getCaseIdByRowNum(String caseId) {
        return apiDetailMap.get(caseId).getRowNum();
    }

    public static void main(String[] args) {
//        System.out.println(apiDetailMap.get("1").getRequestParams());
    }
}
