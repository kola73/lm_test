package main.java.com.kola.java.lesson16;

public class Member {
    int id;
    String name;
    String pwd;
    String mobilePhone;
    int userType;
    double amonut;

    // 构造函数
    public Member() {

    }

    // 带参构造函数：对数据进行初始化，非必须要创建，如果不创建，系统会默认带一个无参构造函数
    // 如果已有带参构造函数，还想要调用无参构造函数，必须重新定义一个
    public Member(int id, String name, String pwd, String mobilePhone, int userType, double amonut) {
        this.id = id;  //每个对象，构造方法中间，都有隐藏的this对象，指代的是当前对象
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.userType = userType;
        this.amonut = amonut;
    }

    public void register(String mobilePhone, String pwd) {
        if (mobilePhone.length() == 11 && pwd.length() >= 6 && pwd.length() <= 11) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败");
        }
    }
}
