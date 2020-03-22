package com.kola.java.lesson32;

import java.io.*;
import java.util.Properties;

/*
加载properties文件的三种方式
 */
public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        // 1,绝对路径
        InputStream is = new FileInputStream(new File("C:\\Users\\YH\\IdeaProjects\\lm_test\\src\\main\\resources\\jdbc.properties"));
        // 2,相对路径
        InputStream is2 = new FileInputStream(new File("src/main/resources/jdbc.properties"));
        // 3,类路径下根路径
        InputStream is3 = Properties.class.getResourceAsStream("/jdbc.properties");
        properties.load(is);
        System.out.println(properties.get("jdbc.pwd"));
    }
}
