package com.kola.java.lesson27;

import java.io.*;

// 字节流
// 复制一个txt文档
public class WriterAndReader {
    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File sourceFile = new File("C:\\Users\\YH\\Desktop\\YCSMS1001-20200317183444-PR0059D132003170031.xml");
            File targetFile = new File("C:\\Users\\YH\\Desktop\\31.xml");
            bufferedReader = new BufferedReader(new FileReader(sourceFile)); // 缓冲区，增强性能
            bufferedWriter = new BufferedWriter(new FileWriter(targetFile));
            while (true) {
                String line = bufferedReader.readLine(); // 读取文件
                if (line == null) {  // 如果为空，表示读取到最后一行，所以我们直接退出循环
                    break;
                } else {
                    bufferedWriter.write(line);  //写入文件
                    bufferedWriter.newLine();   // 换行
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close(); //关闭输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();  //关闭输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
