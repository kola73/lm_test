package main.java.com.kola.java.lesson16;

public class Test {
    public static void main(String[] args) {
        // 构造方法的好处：方便创建对象
        Member tom = new Member(1, "tom", "123455", "13211111111", 1, 1000.00);
        System.out.println(tom.amonut);
        // 否则这边就要这么写
        tom.pwd = "xxxxxx";
        System.out.println(tom.pwd);
    }
}

