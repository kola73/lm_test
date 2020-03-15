package main.java.com.kola.java.lesson19;

/*
接口特点：
1，所有方法都是抽象的
2，只能定义常量：public static final
3，接口不能实例化
4，一个类可以实现多个接口
5，一个接口可以继承自多个接口
6，接口不能继承自类
 */
public interface Login {
    // 常量--不可改变
    public static final String COUNTRY = "中国";

    // 登录(抽象方法：没有方法体）
    public abstract void login(String mobilePhone, String pwd);
}
