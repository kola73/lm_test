package com.kola.java.lesson11;

public class Foreach {
    public static void main(String[] args) {
        String[][] name = {{"lucy", "lily"}, {"tom", "jack", "rose"}};
        for (String[] nameArr : name) {
            for (String names : nameArr) {
                System.out.println(names);
            }
        }
    }
}
