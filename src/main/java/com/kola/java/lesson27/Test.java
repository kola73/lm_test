package com.kola.java.lesson27;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            File sourceFile = new File("C:\\Users\\YH\\Desktop\\门店退供申请导入模板V1.1 (1).xls");
            File targetFile = new File("C:\\Users\\YH\\Desktop\\1.xls");
            reader = new BufferedReader(new FileReader(sourceFile));
            writer = new BufferedWriter(new FileWriter(targetFile));
            while (true) {
                String lines = reader.readLine();
                if (lines == null) {
                    break;
                } else {
                    writer.write(lines);
                    writer.newLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
