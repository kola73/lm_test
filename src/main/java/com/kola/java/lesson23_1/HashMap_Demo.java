package main.java.com.kola.java.lesson23_1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
HashMap:
键值对:key-value
key不能重复，否则后者会覆盖前者
 */
public class HashMap_Demo {
    public static void main(String[] args) {
        HashMap<String, String> info = new HashMap<>();
        // 放东西
        info.put("name", "koal");
        info.put("name", "tom");
        info.put("age", "13");
        info.put("gender", "男");
        // 取的某个key的value
        System.out.println(info.get("name"));  // key不能重复，否则后者会覆盖前者
        // 移除
        info.remove("gender");
        System.out.println(info.get("gender")); // 返回null
        // 是否包含
        System.out.println(info.containsKey("name"));
        System.out.println(info.containsValue("13"));
        System.out.println("=================");
        // 获得所有的key
        Set<String> key = info.keySet();
        for (String keys : key) {
            System.out.println(keys);
        }
        // 获得所有value
        Collection<String> values = info.values();
        for (String value : values) {
            System.out.println(value);
        }
        System.out.println("====================");
        // 获得所有key，value
        // 方法1
        Set<String> allkey = info.keySet();
        for (String keys : allkey) {
            String value = info.get(keys);
            System.out.println(keys + " = " + value);
        }
        // 方法2
        Set<Map.Entry<String, String>> sets = info.entrySet();
        for (Map.Entry<String, String> set : sets) {
            String entryKey = set.getKey();
            String entryValue = set.getValue();
            System.out.println(entryKey + " = " + entryValue);
        }
    }
}
