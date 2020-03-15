package main.java.com.kola.java.lesson19;


public class Borrower extends Member {
    public void borrow(int money) {
        System.out.println("借钱");
    }

    public void recharge(double amonut) {
        System.out.println("开启小额充值通道");
        super.recharge(amonut);
    }

    @Override
    public void login(String mobilePhone, String pwd) {
        // 借款人的登录方法
    }
}
