package main.java.com.kola.java.lesson27;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) {
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream("C:\\Users\\YH\\Pictures\\Screenshots\\屏幕截图(1).png");
            os = new FileOutputStream("C:\\Users\\YH\\Pictures\\Screenshots\\4.png");
            byte[] datas = new byte[1024];
            while (true) {
                int length = is.read(datas);
                if (length == -1) {
                    break;
                } else {
                    os.write(datas, 0, length);
                }
            }

        } catch (Exception e) {
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
