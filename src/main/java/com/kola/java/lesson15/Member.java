package main.java.com.kola.java.lesson15;

public class Member {
    int id;
    String name;
    String pwd;
    String mobilePhone;
    int userType;
    double amonut;

    public void register(String mobilePhone, String pwd) {
        if (mobilePhone.length() == 11 && pwd.length() >= 6 && pwd.length() <= 11) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败");
        }
    }
}
