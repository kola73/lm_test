package com.kola.java.lesson21;

import com.kola.java.lesson21.future.Borrower;
import com.kola.java.lesson21.future.Investor;
import com.kola.java.lesson21.future.Member;

import java.util.ArrayList;
import java.util.Iterator;

/*
ArrayList：数组：有序可重复
 */
public class ArrayList_Demo {
    public static void main(String[] args) {
        ArrayList age = new ArrayList();
        // 添加对象
        age.add(1);
        age.add(2);
        // 获得对象
        System.out.println(age.get(0));
        // 获得数组长度
        System.out.println(age.size());
        // 移除
        age.remove(0);
        System.out.println(age.get(0)); // 移除后后面的元素补上，所以输出结果为2
        // 判空
        System.out.println(age.isEmpty());
        // 包含
        System.out.println(age.contains(3));
        // 一般用这种容器的时候，会去固定类型--》泛型：约束类型
        ArrayList<Member> members = new ArrayList<>();
        Investor investor = new Investor();
        Borrower borrower = new Borrower();
        members.add(borrower);
        members.add(investor);
        members.add(borrower); // 索引可重复
        System.out.println(members.size()); //3
        // 循环遍历得到具体的值
        ArrayList<String> names = new ArrayList<>();
        names.add("tom");
        names.add("jack");
        // 方法1
        for (String name : names) {
            System.out.println(name);
        }
        // 方法2
        for (int i = 1; i < names.size(); i++) {
            System.out.println(names);
        }
        // 方法3：通过迭代器获取
        Iterator<String> name = names.iterator();
        while (name.hasNext()) {  //hasNext:有下一个
            System.out.println(name.next());  //next：获取下一个元素
        }
    }
}
