package main.java.com.kola.java.lesson15;

public class Test {
    public static void main(String[] args) {
        Member tom = new Member();
        tom.pwd = "123455";
        tom.mobilePhone = "13911111111";
        tom.register(tom.mobilePhone, tom.pwd);
    }
}
