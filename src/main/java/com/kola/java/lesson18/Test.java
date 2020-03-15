package main.java.com.kola.java.lesson18;

public class Test {
    public static void main(String[] args) {
        // 通过方法重写，用父类类型，来接收子类对象，不同的对象调用相同方法表现出不同的行为特征和响应，这就叫多态
        Member borrower = new Borrower();
//        borrower.recharge(100.00);
        Member investor = new Investor();
        investor.recharge(3000.00);
        investor.withdraw(10);
        System.out.println("可用余额：" + investor.getAmonut());
        investor.withdraw(1000);
        System.out.println("可用余额：" + investor.getAmonut());
        investor.withdraw(1000);
        System.out.println("可用余额：" + investor.getAmonut());
        investor.withdraw(2000);
        System.out.println("可用余额：" + investor.getAmonut());
    }
}
