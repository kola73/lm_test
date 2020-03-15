package main.java.com.kola.java.lesson17;

public class Test {
    public static void main(String[] args) {
        Member member = new Member();
        // 设值，取值
        member.setAmonut(19000);
        System.out.println(member.getAmonut());
        member.setMobilePhone("134222");
        System.out.println(member.getMobilePhone());
        Investor investor = new Investor();
        investor.register("12344444444", "123444");
        Borrower borrower = new Borrower();
        borrower.register("12344444444", "123444");
    }
}
