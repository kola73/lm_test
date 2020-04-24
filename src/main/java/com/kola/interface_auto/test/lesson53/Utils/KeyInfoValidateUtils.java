package com.kola.interface_auto.test.lesson53.Utils;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.kola.interface_auto.test.lesson53.pojo.CellData;
import com.kola.interface_auto.test.lesson53.pojo.SQLChecker;
import com.kola.interface_auto.test.lesson53.pojo.ValidateResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 关键信息比对（提取响应json数据里的部分数据进行校验）
 * 拓展：在apiDeail那列把预期结果和实际结果旁边新增两列，展示要提取的内容
 * 		  1、关键信息验证-->对应pojo类KeyInfo
 * 		  [
 *            {"jsonPath":"$..xxx.xxx[1]","value":"1"},
 *            {"jsonPath":"$..xxx.xxx[2]","value":"aaa"},
 *            {"jsonPath":"$..xxx.xxx[3]","value":"bbb"}
 * 		  ]
 * 		  2、参数化--把其他接口依赖的信息保存到全局数据池，方便调用
 * 		    [
 *            {"jsonPath":"$..xxx.xxx[1].loanId","value":"loanId"},	-->从这个接口提取出loanId
 *            {"jsonPath":"$..xxx.xxx[2]","value":"aaa"},
 *            {"jsonPath":"$..xxx.xxx[3]","value":"bbb"}
 * 		  ]
 */

/**
 * 提取有效信息
 * preCheckSQL：excel里面的前置验证sql（json格式）
 * expression:要写入的jsonPath表达式
 */
public class KeyInfoValidateUtils {

    public static String getInvalidInfo(String preCheckSQL, String expression) {
        //上面的方式每次都会读取并解析这个json字符串，可以采用如下方式读取并解析json字符串一遍：
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(preCheckSQL);
        // 担心报类型转换异常，可以这么用
        String invalidInfo = JsonPath.parse(document).read(expression);
        return invalidInfo;
    }

    // 前置验证（传的值是行号，列号，前置验证sql的json文件）
    public static void preKeyInfoValidate(String caseId, int cellNum, String preCheckSQL) {
        // 判空
        if (StringUtils.isEmpty(preCheckSQL)) {
            return;
        }
        // 创建一个容器，保存验证结果
        List<ValidateResult> validateResultList = new ArrayList<>();
        // 解析前后置预期结果的json文件
        List<SQLChecker> sqlCheckerList = JSONObject.parseArray(preCheckSQL, SQLChecker.class);
        // 循环遍历，得到前后置预期结果的sql里具体的值
        for (SQLChecker sqlChecker : sqlCheckerList) {
            String no = sqlChecker.getNo();
            String sql = sqlChecker.getSql();
            // LinkedHashMap：这个是有序的哈希
            List<LinkedHashMap<String, String>> expectedResult = sqlChecker.getExpectedResult();
            // 把json类型转换为字符串
            String expectedResults = JSONObject.toJSONString(expectedResult);
            System.out.println(expectedResults);
            // 拿到sql去访问数据库
            List<LinkedHashMap<String, String>> actualResult = DBUtils.select(sql);
            String actualResults = JSONObject.toJSONString(actualResult);
            System.out.println(actualResults);
            // 预期结果和实际结果进行比对
            // 回写结果到excel中去
            if (actualResults.equals(expectedResults)) {
                System.out.println("验证通过");
                // 回写结果到excel对应的测试用例
                validateResultList.add(new ValidateResult(no, expectedResult, "success"));
            } else {
                System.out.println("验证失败");
                validateResultList.add(new ValidateResult(no, expectedResult, "fail"));
            }
            // 拿到要回写的结果
            String validataResultToWrite = JSONObject.toJSONString(validateResultList);
            System.out.println("以下是要回写的结果");
            System.out.println(validataResultToWrite);
            // 收集到回写数据池之间
            ExcelUtils_v8.addCellData(new CellData(caseId, cellNum, validataResultToWrite));

        }
    }

    // 后置验证
    public static void afterKeyInfoValidate(String caseId, int cellNum, String afterCheckSQL) {
        // 判空
        if (StringUtils.isEmpty(afterCheckSQL)) {
            return;
        }
        // 解析json
        List<SQLChecker> sqlCheckerList = JSONObject.parseArray(afterCheckSQL, SQLChecker.class);
        // 创建一个容器，保存验证结果
        List<ValidateResult> validateResultList = new ArrayList<>();
        for (SQLChecker sqlChecker : sqlCheckerList) {
            String no = sqlChecker.getNo();
            String sql = sqlChecker.getSql();
            // LinkedHashMap：这个是有序的哈希
            List<LinkedHashMap<String, String>> expectedResult = sqlChecker.getExpectedResult();
            // 把json类型转换为字符串
            String expectedResults = JSONObject.toJSONString(expectedResult);
            System.out.println(expectedResults);
            // 拿到sql去访问数据库
            List<LinkedHashMap<String, String>> actualResult = DBUtils.select(sql);
            String actualResults = JSONObject.toJSONString(actualResult);
            System.out.println(actualResults);
            // 预期结果和实际结果进行比对
            // 回写结果到excel中去
            if (actualResults.equals(expectedResults)) {
                System.out.println("验证通过");
                // 回写结果到excel对应的测试用例
                validateResultList.add(new ValidateResult(no, expectedResult, "success"));
            } else {
                System.out.println("验证失败");
                validateResultList.add(new ValidateResult(no, expectedResult, "fail"));
            }
            // 拿到要回写的结果
            String validataResultToWrite = JSONObject.toJSONString(validateResultList);
            System.out.println("以下是要回写的结果");
            System.out.println(validataResultToWrite);
            // 收集到回写数据池之间
            ExcelUtils_v8.addCellData(new CellData(caseId, cellNum, validataResultToWrite));
        }
    }


    public static void main(String[] args) {
        String json = "{\n" +
                "    \"code\":0,\n" +
                "    \"data\":{\n" +
                "        \"total\":3076,\n" +
                "        \"pagesize\":10,\n" +
                "        \"pageindex\":1,\n" +
                "        \"rows\":[\n" +
                "            {\n" +
                "                \"note\":\"\",\n" +
                "                \"updatedby\":\"10001\",\n" +
                "                \"logisticsname\":\"直通\",\n" +
                "                \"skucode\":\"949218\",\n" +
                "                \"createduser\":\"admin\",\n" +
                "                \"logistics\":4,\n" +
                "                \"approvaltypename\":\"辉创\",\n" +
                "                \"skuname\":\"鲜活东星斑\",\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"createdat\":1587552735052,\n" +
                "                \"approvaltype\":1,\n" +
                "                \"createdby\":\"10001\",\n" +
                "                \"shopname\":\"上海市田林路会员店\",\n" +
                "                \"id\":1003685,\n" +
                "                \"iseffective\":true,\n" +
                "                \"updateduser\":\"admin\",\n" +
                "                \"updatedat\":1587552741363\n" +
                "            },\n" +
                "            {\n" +
                "                \"note\":\"\",\n" +
                "                \"updatedby\":\"10001\",\n" +
                "                \"logisticsname\":\"直通\",\n" +
                "                \"skucode\":\"949217\",\n" +
                "                \"createduser\":\"admin\",\n" +
                "                \"logistics\":4,\n" +
                "                \"approvaltypename\":\"辉创\",\n" +
                "                \"skuname\":\"鲜活青斑鱼\",\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"createdat\":1587552718353,\n" +
                "                \"approvaltype\":1,\n" +
                "                \"createdby\":\"10001\",\n" +
                "                \"shopname\":\"上海市田林路会员店\",\n" +
                "                \"id\":1003684,\n" +
                "                \"iseffective\":true,\n" +
                "                \"updateduser\":\"admin\",\n" +
                "                \"updatedat\":1587552741361\n" +
                "            },\n" +
                "            {\n" +
                "                \"note\":\"\",\n" +
                "                \"updatedby\":\"10001\",\n" +
                "                \"logisticsname\":\"直通\",\n" +
                "                \"skucode\":\"641\",\n" +
                "                \"createduser\":\"admin\",\n" +
                "                \"logistics\":4,\n" +
                "                \"approvaltypename\":\"辉创\",\n" +
                "                \"skuname\":\"全国苹果818\",\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"createdat\":1587552697943,\n" +
                "                \"approvaltype\":1,\n" +
                "                \"createdby\":\"10001\",\n" +
                "                \"shopname\":\"上海市田林路会员店\",\n" +
                "                \"id\":1003683,\n" +
                "                \"iseffective\":true,\n" +
                "                \"updateduser\":\"admin\",\n" +
                "                \"updatedat\":1587552741359\n" +
                "            },\n" +
                "            {\n" +
                "                \"note\":\"\",\n" +
                "                \"updatedby\":\"10001\",\n" +
                "                \"logisticsname\":\"直送\",\n" +
                "                \"skucode\":\"1009618\",\n" +
                "                \"createduser\":\"admin\",\n" +
                "                \"logistics\":1,\n" +
                "                \"approvaltypename\":\"辉创\",\n" +
                "                \"skuname\":\"星巴克抹茶拿铁250ml\",\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"createdat\":1586861588089,\n" +
                "                \"approvaltype\":1,\n" +
                "                \"createdby\":\"10001\",\n" +
                "                \"shopname\":\"上海市田林路会员店\",\n" +
                "                \"id\":1003679,\n" +
                "                \"iseffective\":true,\n" +
                "                \"updateduser\":\"admin\",\n" +
                "                \"updatedat\":1586861634293\n" +
                "            },\n" +
                "            {\n" +
                "                \"note\":\"\",\n" +
                "                \"updatedby\":\"10001\",\n" +
                "                \"logisticsname\":\"直通\",\n" +
                "                \"categorycode\":\"12\",\n" +
                "                \"createduser\":\"admin\",\n" +
                "                \"logistics\":4,\n" +
                "                \"categorytypename\":\"部类\",\n" +
                "                \"approvaltypename\":\"辉创\",\n" +
                "                \"categorytype\":1,\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"createdat\":1583747822269,\n" +
                "                \"approvaltype\":1,\n" +
                "                \"createdby\":\"10001\",\n" +
                "                \"shopname\":\"上海市田林路会员店\",\n" +
                "                \"categoryname\":\"食品类\",\n" +
                "                \"id\":1003661,\n" +
                "                \"iseffective\":true,\n" +
                "                \"updateduser\":\"admin\",\n" +
                "                \"updatedat\":1583748072218\n" +
                "            },\n" +
                "            {\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"note\":\"\",\n" +
                "                \"createdat\":1583735414132,\n" +
                "                \"logisticsname\":\"直通\",\n" +
                "                \"approvaltype\":1,\n" +
                "                \"skucode\":\"247744\",\n" +
                "                \"logistics\":4,\n" +
                "                \"id\":998659,\n" +
                "                \"iseffective\":true,\n" +
                "                \"approvaltypename\":\"辉创\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"note\":\"\",\n" +
                "                \"createdat\":1583735414132,\n" +
                "                \"logisticsname\":\"直通\",\n" +
                "                \"approvaltype\":1,\n" +
                "                \"skucode\":\"247742\",\n" +
                "                \"logistics\":4,\n" +
                "                \"id\":998658,\n" +
                "                \"iseffective\":true,\n" +
                "                \"approvaltypename\":\"辉创\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"note\":\"\",\n" +
                "                \"createdat\":1583735414132,\n" +
                "                \"logisticsname\":\"直通\",\n" +
                "                \"approvaltype\":1,\n" +
                "                \"skucode\":\"247741\",\n" +
                "                \"logistics\":4,\n" +
                "                \"id\":998657,\n" +
                "                \"iseffective\":true,\n" +
                "                \"approvaltypename\":\"辉创\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"note\":\"\",\n" +
                "                \"createdat\":1583735414132,\n" +
                "                \"logisticsname\":\"直通\",\n" +
                "                \"approvaltype\":1,\n" +
                "                \"skucode\":\"247681\",\n" +
                "                \"logistics\":4,\n" +
                "                \"id\":998656,\n" +
                "                \"iseffective\":true,\n" +
                "                \"approvaltypename\":\"辉创\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"shopcode\":\"9D13\",\n" +
                "                \"note\":\"\",\n" +
                "                \"createdat\":1583735414132,\n" +
                "                \"logisticsname\":\"直通\",\n" +
                "                \"approvaltype\":1,\n" +
                "                \"skucode\":\"247679\",\n" +
                "                \"logistics\":4,\n" +
                "                \"id\":998655,\n" +
                "                \"iseffective\":true,\n" +
                "                \"approvaltypename\":\"辉创\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"now\":1587634917117\n" +
                "}";
        List<String> skucodes = JsonPath.read(json, "$.data.rows[*].skuname");
        System.out.println(skucodes);
        List<String> logistics = JsonPath.read(json, "$..logistics");
        System.out.println(logistics);
        //上面的方式每次都会读取并解析这个json字符串，可以采用如下方式读取并解析json字符串一遍：
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        // 担心报类型转换异常，可以这么用
        String skuname = JsonPath.parse(document).read("$.data.rows[1].skuname");
        System.out.println(skuname);

    }
}
