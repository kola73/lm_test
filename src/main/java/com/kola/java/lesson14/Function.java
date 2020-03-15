package main.java.com.kola.java.lesson14;

// 计算数组的和

public class Function {
    public static int add(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int items = arr[i];
            sum += items;
        }
        return sum;
    }

    // 可变参数
    public static int add2(int... args) {
        int sum = 0;
        for (int i : args) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arrs = {1, 2, 3, 4, 5};
        System.out.println(add(arrs));
        System.out.println(add2(arrs));
    }
}
