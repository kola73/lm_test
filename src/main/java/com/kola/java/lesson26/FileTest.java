package com.kola.java.lesson26;

import java.io.File;
import java.io.IOException;

/*
输入输出流
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
//        File file = new File("C:\\Users\\YH\\Desktop\\1.TXT");
//        if (!file.exists()) {
//            file.createNewFile();  // 创建新的文件
//        }
//        System.out.println(file.isFile());  //判断是否是文件
//        System.out.println(file.isDirectory());   // 判断是否是目录
//        File file2 = new File("C:\\Users\\YH\\Desktop\\hello");
//        if (!file2.exists()) {
//            file2.mkdir(); // 创建单层目录
//            file2.mkdirs(); // 创建多层目录
//        }
//        File file3 = new File("C:\\Users\\YH\\Desktop\\hello");
//        File[] files = file3.listFiles();
//        for (File allFile : files) {
//            System.out.println(allFile.getName());   // 获得名称
//            System.out.println(allFile.getAbsoluteFile());  // 获得绝对路径
//        }
        File file4 = new File("C:\\Users\\YH\\Desktop\\hello\\readme.md");
        // delete只能删除文件和空的目录
        boolean isDel = file4.delete();
        System.out.println(isDel);

    }
}
