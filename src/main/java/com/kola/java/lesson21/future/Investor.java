package main.java.com.kola.java.lesson21.future;


public class Investor extends Member {
    public void invest(int money) {
        System.out.println("投资资金");
    }

    // 多态：关键词：super
    public void recharge(double amount) {
        System.out.println("投资人充值");
        super.recharge(amount);
    }

    @Override
    public void login(String mobilePhone, String pwd) {
        // 投资人的登录方法
    }

    /*
    1，投资金额必须是10的正整数倍
    2，投资金额必须大于可用余额
    3，投资金额必须小于标的可投余额
     */
    public void invest(double amount) {
        if (amount <= 0 || amount % 10 != 0) {
            System.out.println("投资金额必须是10的正整数倍！");
        } else if (amount > this.getAmonut()) {
            System.out.println("投资失败，投资金额大于可用余额");
        } else {
            this.setAmonut(this.getAmonut() - amount);
            System.out.println("投资成功！");
        }
    }

}
