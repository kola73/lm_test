package main.java.com.kola.java.lesson19;

/*
抽象类
1,抽象类不支持实例化，只能通过子类继承完成，避免了子类设计的随意性
2,子类需要实现抽象类中的全部抽象方法，除非子类也是抽象类
3，抽象类不一定要包含抽象方法，但是有抽象方法的一定是抽象类
抽象方法：
1，abstract关键字
2，没有方法体
 */
public class Test {
    public static void main(String[] args) {
        //抽象类不支持实例化，只能通过子类继承完成
        // Member jack = new Member();
        Member tom = new Investor();
    }
}