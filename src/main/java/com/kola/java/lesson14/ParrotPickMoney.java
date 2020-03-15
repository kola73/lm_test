package main.java.com.kola.java.lesson14;

public class ParrotPickMoney {
    public static boolean validateMoney(int money) {
        if (money < 10) {
            System.out.println("滚！");
            return false;
        } else {
            System.out.println("叼走~");
            return true;
        }
    }

    public static int pickMoney(int money) {
        if (validateMoney(money)) {
            System.out.println(money);
            return money;
        } else {
            System.out.println(0);
            return 0;
        }
    }

    public static void main(String[] args) {
        pickMoney(100);
        pickMoney(2);
    }
}
