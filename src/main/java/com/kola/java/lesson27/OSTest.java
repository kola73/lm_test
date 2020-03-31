package com.kola.java.lesson27;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// 字符流：读取图片之类的
public class OSTest {
    public static void main(String[] args) {
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream("C:\\Users\\YH\\Pictures\\Screenshots\\屏幕截图(1).png");
            os = new FileOutputStream("C:\\Users\\YH\\Pictures\\Screenshots\\2.png");
            // 创建一个buffer缓冲器（容器）
            // 如果没有读取到内容，将返回-1，否则返回到读取的数据的长度
            byte[] data = new byte[1024];
            while (true) {
                int size = is.read(data);
                if (size == -1) {
                    break;
                } else {
                    os.write(data, 0, size);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
