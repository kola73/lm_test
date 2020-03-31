package com.kola.java.lesson19;

/**
 * 封装
 * 抽象类：可以对多个类进行进一步抽象
 * 抽象类和接口的区别；
 * 1，结构：抽象类包含抽象方法，普通方法，常量，变量，构造方法。接口包含抽象方法，全局常量
 * 2，定义：抽象类用abstract定义，接口用interface定义
 * 3，子类：抽象类用extends实现，接口用implements实现
 * 4，限制：一个类只能继承一个抽象类，接口是可以多继承的
 * 5，关系：一个抽象类可以实现多个接口，接口不能继承抽象类，但是可以继承多个接口
 * 6，实例化：二者都是通过子类实例化
 * 7，特征：抽象类是模板设计模式，接口一种标准，能力
 */
public abstract class Member implements InnerUser,Login {
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

//    // 登录(抽象方法：没有方法体）
//    public abstract void login(String mobilePhone, String pwd);
}
