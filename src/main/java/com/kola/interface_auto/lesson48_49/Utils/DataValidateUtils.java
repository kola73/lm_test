package com.kola.interface_auto.lesson48_49.Utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.kola.interface_auto.lesson48_49.pojo.CellData;
import com.kola.interface_auto.lesson48_49.pojo.SQLChecker;
import com.kola.interface_auto.lesson48_49.pojo.ValidateResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/*
数据验证：
数据---》数据库
数据验证--》查询数据库
什么时候验证：
1，执行测试用例前（前置数据验证）
2，执行测试用例后（后置数据验证）
怎么验证：
查询数据库--jdbc
注册功能：
正向测试用例：
admin，123456a
前置：查询admin不存在
no：1 select count(1)from user where username="admin"; --> 一条记录，单个字段
预期结果：1

后置：完成测试用例后，发现admin已经存在在库里
select count(1)from user where username="admin";
预期结果：1
{
   "no":"1",
   "sql":"select count(1)from sys_user where login_name='admin';",
   "expectedResult":[
     {"count(1)":"1"}
  ]
}
no：2 select login_name,password from sys_user where login_name='admin'; --> 一条记录，多个字段
预期结果：
username:xxx,password:xxx
{
   "no":"2",
   "sql":"select login_name,password from sys_user where username='admin';",
   "expectedResult":[
     {"login_name":"admin","password":"efc4fab2ae3f7ef3bf9addee5458e7b21906ed07"},
  ]
}
分页查询：
no：3 select id,login_name,locked from sys_user limit 0,3; --> 多条记录，多个字段
预期结果：
1	13917855468	1
3	13524333501	1
4	13817076343	1

包装成下面的json格式
{
   "no":"3",
   "sql":"select id,login_name,locked from sys_user limit 0,3;",
   "expectedResult":[
   {"id":"1","login_name":"13917855468","locked":"1"},
     {"id":"3","login_name":"13524333501","locked":"1"},
     {"id":"4","login_name":"13817076343","locked":"1"}
  ]
}
上面的sql，每条sql的期望结果都应该保存到excel中--->string--->json
[{
   "no":"1",
   "sql":"select count(1)from user where login_name='admin';",
   "expectedResult":[{"count(1)":"1"}]
},
{
   "no":"2",
   "sql":"select login_name,password from sys_user where login_name='admin';",
   "expectedResult":[{"login_name":"admin","password":"efc4fab2ae3f7ef3bf9addee5458e7b21906ed07"}]
},
{
   "no":"3",
   "sql":"select id,login_name,locked from sys_user limit 0,3;",
   "expectedResult":[
     {"id":"1","login_name":"13917855468","locked":"1"},
     {"id":"3","login_name":"13524333501","locked":"1"},
     {"id":"4","login_name":"13817076343","locked":"1"}
  ]
}
]
上面的sql和期望结果都应该存入excel
改动点：
1，为了防止预期结果和实际结果因为返回的顺序，或者是大小写的问题造成的验证不通过，我们可以用别名的方式对我们的sql进行修改，比如
{
   "no":"2",
   "sql":"select login_name as name,password as pwd from sys_user where login_name='admin';",
   "expectedResult":[{"name":"admin","pwd":"efc4fab2ae3f7ef3bf9addee5458e7b21906ed07"}]
},
记得在dbutils类也要修改一个地方：
// 获得别名
String columnName = metaData.getColumnLabel(i);
这样输出的结果就是
[{"name":"admin","pwd":"efc4fab2ae3f7ef3bf9addee5458e7b21906ed07"}]
[{"name":"admin","pwd":"efc4fab2ae3f7ef3bf9addee5458e7b21906ed07"}]
验证通过
2,excel里新增了4个字段，前置验证sql，前置验证结果，后置验证sql，后置验证结果，对应的apiDetail的pojo类也对应新增了这几个

 */
public class DataValidateUtils {
    // 前置验证（传的值是行号，列号，前置验证sql的json文件）
    public static void preValidate(String caseId, int cellNum, String preCheckSQL) {
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
            ExcelUtils_v7.addCellData(new CellData(caseId, cellNum, validataResultToWrite));

        }
    }

    // 后置验证
    public static void afterValidate(String caseId, int cellNum, String afterCheckSQL) {
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
            ExcelUtils_v7.addCellData(new CellData(caseId, cellNum, validataResultToWrite));
        }
    }

    //example
    public static void main(String[] args) {
        // 解析json
        String sqls = "[{\n" +
                "   \"no\":\"1\",\n" +
                "   \"sql\":\"select count(1)from sys_user where login_name='admin';\",\n" +
                "   \"expectedResult\":[{\"count(1)\":\"1\"}]\n" +
                "},\n" +
                "{\n" +
                "   \"no\":\"2\",\n" +
                "   \"sql\":\"select login_name as name,password as pwd from sys_user where login_name='admin';\",\n" +
                "   \"expectedResult\":[{\"name\":\"admin\",\"pwd\":\"efc4fab2ae3f7ef3bf9addee5458e7b21906ed07\"}]\n" +
                "},\n" +
                "{\n" +
                "   \"no\":\"3\",\n" +
                "   \"sql\":\"select id,login_name,locked from sys_user limit 0,3;\",\n" +
                "   \"expectedResult\":[\n" +
                "     {\"id\":\"1\",\"login_name\":\"13917855468\",\"locked\":\"1\"},\n" +
                "     {\"id\":\"3\",\"login_name\":\"13524333501\",\"locked\":\"1\"},\n" +
                "     {\"id\":\"4\",\"login_name\":\"13817076343\",\"locked\":\"1\"}\n" +
                "  ]\n" +
                "}\n" +
                "]";
        List<SQLChecker> sqlCheckerList = JSONObject.parseArray(sqls, SQLChecker.class);
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
            /* 回写结果到excel中去
            [
            {"no":"1","expectedResult":[{},{}],"result":"success"},
            {"no":"2","expectedResult":[{},{}],"result":"fail"},
            {"no":"3","expectedResult":[{},{}],"result":"fail"}
            ]
             */
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

        }
    }

    // 练习版本
    public static void preSqlValidate(String caseId, int cellNum, String preCheckSql) {
        // 如果传的sql为空，自动退出循环
        if (StringUtils.isEmpty(preCheckSql)) {
            return;
        }
        // 创建容器，存放验证结果
        List<ValidateResult> validateResultList = new ArrayList<>();
        // 循环遍历，得到预处理文件具体的值
        List<SQLChecker> preSql = new ArrayList<>();
        for (SQLChecker sqlChecker : preSql) {
            String no = sqlChecker.getNo();
            String sql = sqlChecker.getSql();
            List<LinkedHashMap<String, String>> expectedResult = sqlChecker.getExpectedResult();
            String expectResults = JSONObject.toJSONString(expectedResult);
            // 访问数据库，得到实际的结果
            List<LinkedHashMap<String, String>> actualResult = DBUtils.select(sql);
            String actualResults = JSONObject.toJSONString(actualResult);
            // 把实际结果和预期结果进行比对
            if (actualResults.equals(expectResults)) {
                System.out.println("验证成功");
                validateResultList.add(new ValidateResult(no, expectedResult, "pass"));
            } else {
                System.out.println("验证失败！");
                validateResultList.add(new ValidateResult(no, expectedResult, "fail"));
            }
            // 拿到回写的结果
            String validateResultLists = JSONObject.toJSONString(validateResultList);
            // 把结果的值写回excel
            ExcelUtils_v7.addCellData(new CellData(caseId, cellNum, validateResultLists));
        }


    }
}