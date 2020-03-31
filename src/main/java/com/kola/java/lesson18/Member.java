package com.kola.java.lesson18;

/**
 * 封装
 */
public class Member {
    private int id;
    private String name;
    private String pwd;
    private String mobilePhone;
    private int userType;
    private double amonut;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setMobilePhone(String mobilePhone) {
        if (mobilePhone.length() == 11) {
            this.mobilePhone = mobilePhone;
        }
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public void setAmonut(double amonut) {
        this.amonut = amonut;
    }

    public double getAmonut() {
        return amonut;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", userType=" + userType +
                ", amonut=" + amonut +
                '}';
    }

    // 注册
    public void register(String mobilePhone, String pwd) {
        if (mobilePhone.length() == 11 && pwd.length() >= 6 && pwd.length() <= 11) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败");
        }
    }

    // 充值
    public void recharge(double amonut) {
        if (amonut < 100) {
            System.out.println("充值失败：充值金额应该大于100");
            System.out.println("可用金额：" + amonut);
        } else {
            System.out.println("充值成功");
            this.amonut += amonut;
            System.out.println("可用金额：" + amonut);
        }
    }

    // 提现
    public boolean withdraw(double amonut) {
        if (amonut < 100 || amonut > 500000) {
            System.out.println("提现失败，提现金额最小为100元，最大为500000元");
            return false;
        } else if (amonut > this.amonut) {
            System.out.println("提现失败,提现金额大于可用余额");
            return false;
        } else {
            System.out.println("提现成功");
            this.amonut -= amonut;
            return true;
        }
    }

}
