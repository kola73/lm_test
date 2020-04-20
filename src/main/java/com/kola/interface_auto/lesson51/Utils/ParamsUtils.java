package com.kola.interface_auto.lesson51.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamsUtils {
    /*
   痛点：excel里面的请求数据固定，比如手机号
   解决方法：
   1，删除数据库 --不好
   2，操作excel，全局替换 --不好
   3，程序查询出最大的手机号+1--》新手机号--》把excel中间的数据进行替换（参数化--》接口的关联 ）
   从数据库查出用户名--内存--变量
    */
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
        // 替换完之后，拿到替换的参数进行方法的处理
        return getFunctionOptStr(str);
    }

    /**
     * 通过反射调用FunctionUtils中的方法，得到处理字符串的值
     * 总体思想：
     * 通过正则匹配出方法参数，方法名等，然后以方法名传入参数，去调用方法，得到最终的结果，并把上面的字符串（如“__getUserName()”）替换掉
     *
     * @param str
     */
    public static String getFunctionOptStr(String str) {
        String regex = "__(\\w*?)\\(((\\w*?)*)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        // 拿到类的字节码对象
        Class clazz = FunctionUtils.class;
        while (matcher.find()) {
            // 通过正则匹配到完整的方法参数
            String totalStr = matcher.group(0);
            // 拿到方法名
            String functionName = matcher.group(1);
            // 拿到所有参数
            String params = matcher.group(2);
            String result = "";
            if ("".equals(params)) {
                try {
                    Method method = clazz.getMethod(functionName);
                    result = (String) method.invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // 参数值的数组
                String[] paramsArray = params.split(",");
                // 获得参数值的个数
                int paramsLength = paramsArray.length;
                // 定义一个字节码对象的数组（反射时候需要用到）
                // 后面传的的参数类型本来是string.class,但是因为string.class是字节码对象，所以定义的类型是class数组
                Class[] paramTypes = new Class[paramsArray.length];  //这里是空的
                for (int i = 0; i < paramsLength; i++) {
                    // 每一个参数的类型都设置为string类型（之前规定的就是参数都是string类型）
                    paramTypes[i] = String.class;
                }
                try {
                    Method method = clazz.getMethod(functionName, paramTypes);
                    //拿到方法名进行反射调用functionUtils中对应的方法(调用静态方法是不需要写的，类方法才需要，所以这里第一个字段传空）
                    result = (String) method.invoke(null, paramsArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            str = str.replace(totalStr, result);

        }
        return str;
    }

    public static void main(String[] args) throws Exception {
        globalDataMap.put("username", "wenhuijuan");
        String str1 = "{\"username\":\"__getUserName()\",\"password\":\"__md5(123456a)\"}";
        System.out.println(getFunctionOptStr(commonStr(str1)));
    }

    //example
    public void example1() {
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

    // example2
    private static void example2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String str1 = "{\"username\":\"__getUserName()\",\"password\":\"__md5(123456a)\"}";
        System.out.println("替换前：" + str1);
        String regex1 = "__(\\w*?)\\(((\\w*?)*)\\)";
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(str1);
        // 拿到类的字节码对象
        Class clazz = FunctionUtils.class;
        while (matcher.find()) {
            // 通过正则匹配到完整的方法参数
            String totalStr = matcher.group(0);
            // 拿到方法名
            String functionName = matcher.group(1);
            // 拿到所有参数
            String params = matcher.group(2);
            String result = "";
            if ("".equals(params)) {
                Method method = clazz.getMethod(functionName);
                result = (String) method.invoke(null);
            } else {
                // 参数值的数组
                String[] paramsArray = params.split(",");
                // 获得参数值的个数
                int paramsLength = paramsArray.length;
                // 定义一个字节码对象的数组（反射时候需要用到）
                Class[] paramTypes = new Class[paramsArray.length];  //这里是空的
                for (int i = 0; i < paramsLength; i++) {
                    // 每一个参数的类型都设置为string类型（之前规定的就是参数都是string类型）
                    paramTypes[i] = String.class;
                }
                // 后面传的的参数类型本来是string.class,但是因为string.class是字节码对象，所以定义的类型是class数组
                Method method = clazz.getMethod(functionName, paramTypes);
                //拿到方法名进行反射调用functionUtils中对应的方法(调用静态方法是不需要写的，类方法才需要，所以这里第一个字段传空）
                result = (String) method.invoke(null, paramsArray);
            }
            str1 = str1.replace(totalStr, result);
            System.out.println("替换后：" + str1);

        }
    }
}

