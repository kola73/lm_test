package com.kola.interface_auto.lesson44.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

public class ExcelUtils {
    public static Object[][] readExcel(String excelPath, int sheetNum) {
        Object[][] values = null;
        try {
            InputStream is = ExcelUtils.class.getResourceAsStream(excelPath);
            // 创建工作簿对象
            Workbook workbook = WorkbookFactory.create(is);
            // 获得sheet(这里定义从1开始，但是默认是从0开始，所以要-1）
            Sheet sheet = workbook.getSheetAt(sheetNum - 1);
            // 获得最大行
            int lastRowNum = sheet.getLastRowNum();
            // 获得最大列
            Row firstRow = sheet.getRow(0);
            int lastCellNum = firstRow.getLastCellNum();
            values = new Object[lastRowNum][];
            // 循环遍历，得到具体的值
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                Object[] cellValueArray = new Object[lastCellNum];
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    cellValueArray[j] = cellValue;
                }
                values[i - 1] = cellValueArray;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return values;
    }

    public static void main(String[] args) {
        readExcel("/api_v4.xlsx", 1);
    }
}
