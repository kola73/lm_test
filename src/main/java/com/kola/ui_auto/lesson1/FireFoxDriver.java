package com.kola.ui_auto.lesson1;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class FireFoxDriver {
    public static void main(String[] args) {
        try {
            // 设置路径
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "src/main/resources/driver/chromedriver.exe");
            // 实例化对象
            ChromeDriver chromeDriver = new ChromeDriver();
            Thread.sleep(3000);
            // 访问百度
            chromeDriver.get("https://www.baidu.com");
            // 关闭驱动
            chromeDriver.quit();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
