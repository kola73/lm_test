package main.java.com.kola.java.lesson10;

/**
 * 二维数组
 */
public class ArrayTest2 {
    public static void main(String[] args) {
        // 定义一个3行2列的二维数组（下标从1开始）
        int[][] arrays = new int[3][2];
        int[] arr1 = {1, 2};
        int[] arr2 = {1, 2};
        int[] arr3 = {2, 3};
        arrays[0] = arr1;
        arrays[1] = arr2;
        arrays[2] = arr3;
        System.out.println(arrays[2][0]);
        // 静态声明
        int[][] intArray = {{11, 22}, {112, 222, 222}, {1, 2, 3}};
        System.out.println(intArray[2][1]);

    }
}
