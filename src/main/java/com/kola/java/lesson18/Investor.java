package com.kola.java.lesson18;


public class Investor extends Member {
    public void invest(int money) {
        System.out.println("投资资金");
    }

    // 多态：关键词：super
    public void recharge(double amount) {
        System.out.println("投资人充值");
        super.recharge(amount);
    }

}
