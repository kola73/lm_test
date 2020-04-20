package com.kola.interface_auto.lesson50.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
痛点：excel里面的请求数据固定，比如手机号
解决方法：
1，删除数据库 --不好
2，操作excel，全局替换 --不好
3，程序查询出最大的手机号+1--》新手机号--》把excel中间的数据进行替换（参数化--》接口的关联 ）
从数据库查出用户名--内存--变量
 */
public class ParamsUtils {
    // 全局数据池
    static Map<String, String> globalDataMap = new HashMap<>();

    // 添加数据在全局数据之间
    public static void addGlobalData(String key, String value) {
        globalDataMap.put(key, value);
    }

    // 从全局数据中提取出对应的值
    public static String getGlobalValue(String key) {
        return globalDataMap.get(key);
    }

    // 对字符串进行正则匹配，把提取出的符合规则的字符串替换为它对应的值
    public static String commonStr(String str) {
        // 检索，提取符合字符串的内容--正则表达式
        // ${变量名} -->\$\{.*?\}
        String regex = "\\$\\{(.*?)\\}";  // 贪婪模式
        //编译正则表达式，得到模式
        Pattern pettern = Pattern.compile(regex);
        // 进行匹配
        Matcher matcher = pettern.matcher(str);
        // 迭代所有符合规则的字符串
        while (matcher.find()) {
            // group(0):检测到完整的符合规则的字符串
            String totalStr = matcher.group(0);
            // 将参数名提取出来
            String paramsName = matcher.group(1);
            // 以参数名把对应的变量名提取出来
            String paramsValue = globalDataMap.get(paramsName);
            //  把完整的字符串替换为参数对应的值
            str = str.replace(totalStr, paramsValue);
        }
        return str;
    }

    // test
    public static void main(String[] args) {
        globalDataMap.put("username", "admin");
        globalDataMap.put("password", "123456a");
        // ${mobilephone} ---${变量名}
        // 从excel解析出来的数据，把其中的参数替换为内存之间的对应变量的值
        String requestParams = "{\"username\":\"${username}\",\"password\":\"${password}\"}";
        System.out.println(commonStr(requestParams));
//        System.out.println("替换前：" + requestParams);
//        // String requestParams = "{\"username\":\"admin\",\"password\":\"123456a"}";
//        // 检索，提取符合字符串的内容--正则表达式
//        // ${变量名} -->\$\{.*?\}
//        String regex = "\\$\\{(.*?)\\}";  // 贪婪模式
//        //编译正则表达式，得到模式
//        Pattern pettern = Pattern.compile(regex);
//        // 进行匹配
//        Matcher matcher = pettern.matcher(requestParams);
//        // 迭代所有符合规则的字符串
//        while (matcher.find()) {
//            // group(0):检测到完整的符合规则的字符串
//            String totalStr = matcher.group(0);
////            System.out.println(totalStr);
//            // 将参数名提取出来
//            String paramsName = matcher.group(1);
////            System.out.println(paramsName);
//            // 以参数名把对应的变量名提取出来
//            String paramsValue = globalDataMap.get(paramsName);
////            System.out.println(paramsValue);
//            //  把完整的字符串替换为参数对应的值
//            requestParams = requestParams.replace(totalStr, paramsValue);
//        }
//        System.out.println("替换后：" + requestParams);
    }

}