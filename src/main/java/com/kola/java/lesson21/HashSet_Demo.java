package main.java.com.kola.java.lesson21;

import com.kola.java.lesson21.future.Investor;
import com.kola.java.lesson21.future.Member;

import java.util.HashSet;
import java.util.Iterator;

/*
HashSet：无序不可重复
 */
public class HashSet_Demo {
    public static void main(String[] args) {
        HashSet<String> nameset = new HashSet<>();
        // 添加
        nameset.add("jack");
        nameset.add("tom");
        nameset.add("tom");
        nameset.add("lucy");
        // 移除
        nameset.remove("jack");
        // 获得个数
        System.out.println(nameset.size()); //2个
        // 判断是否为空
        System.out.println(nameset.isEmpty());
        // 判断是否包含
        System.out.println(nameset.contains("jack"));
        HashSet<Member> members = new HashSet<>();
        members.add(new Investor());
        members.add(new Investor());
        System.out.println(members.size());  //这里是2个，因为都开辟了新的内存空间
        // 转换为字符串
        Object[] names = nameset.toArray();
        for (Object name : names) {
            String obj = (String) name;  //强转成功的原因：本质类型是String
            System.out.println(obj);
        }
        // 迭代：方法1
        for (String name : nameset) {
            System.out.println(name);
        }
        // 方法2:迭代器
        Iterator<String> name = nameset.iterator();
        while (name.hasNext()) {
            System.out.println(name.next());
        }
    }
}
