package com.kola.interface_auto.MyTest.utils;

import com.kola.interface_auto.MyTest.pojo.ApiInfo;
import com.kola.interface_auto.MyTest.pojo.ApiDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtils {
    private static Map<String, ApiInfo> apiInfoMap = new HashMap<>();
    private static Map<String, ApiDetail> apiDetailMap = new HashMap<>();

    static {
        List<ApiInfo> apiInfoList = (List<ApiInfo>) ExcelUtils.readExcel("/api_v4.xlsx", 1, ApiInfo.class);
        for (ApiInfo apiInfos : apiInfoList) {
            apiInfoMap.put(apiInfos.getApiId(), apiInfos);
        }
        List<ApiDetail> apiDetailList = (List<ApiInfo>) ExcelUtils.readExcel("/api_v4.xlsx", 2, ApiDetail.class);
        for (ApiDetail apiDetail : apiDetailList) {
            apiDetailMap.put(apiDetail.getApiId(), apiDetail);
        }
    }
    public static int getCaseIdByRowNum(int caseId){
        return apiDetailMap.get(caseId).getRowNum();
    }

}
