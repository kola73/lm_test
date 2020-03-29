package com.kola.interface_auto.lesson41;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

public class ExcelUtils_v1 {
    public static void readExcel(String excelPath) {
        try {
            InputStream is = ExcelUtils_v1.class.getResourceAsStream(excelPath);
            // 创建工作簿对象
            Workbook workbook = WorkbookFactory.create(is);
            // 获得第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            // 获得第一行
            Row row = sheet.getRow(0);
            // 获得第一列
            Cell cell = row.getCell(0);
            // 得到每一列的值
            String cellName = cell.getStringCellValue();
            System.out.println(cellName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readExcel("/api.xlsx");
    }
}
