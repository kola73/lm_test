package com.kola.java.lesson11;

/**
 * 循环遍历，得到数组的值
 */
public class ForArray {
    public static void main(String[] args) {
        String[][] name = {{"lucy", "lily"}, {"tom", "jack", "rose"}};
        for (int i = 0; i < name.length; i++) {
            String[] names = name[i];
            for (int j = 0; j < names.length; j++) {
                String arrayNames = names[j];
                System.out.println(arrayNames);
            }
        }

    }
}
