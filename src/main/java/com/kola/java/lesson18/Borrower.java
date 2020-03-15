package main.java.com.kola.java.lesson18;

public class Borrower extends Member {
    public void borrow(int money) {
        System.out.println("借钱");
    }

    public void recharge(double amonut) {
        System.out.println("开启小额充值通道");
        super.recharge(amonut);
    }

}
