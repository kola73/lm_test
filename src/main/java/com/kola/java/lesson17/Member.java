package com.kola.java.lesson17;

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
    public void register(String mobilePhone, String pwd) {
        if (mobilePhone.length() == 11 && pwd.length() >= 6 && pwd.length() <= 11) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败");
        }
    }
}
