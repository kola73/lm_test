package com.kola.interface_auto.lesson43.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

public class ExcelUtils {
    public static Object[][] readExcel(String path, int sheetNum) {
        Object[][] params = null;
        try {
            InputStream inputStream = ExcelUtils.class.getResourceAsStream(path);
            // 获得工作簿对象
            Workbook workbook = WorkbookFactory.create(inputStream);
            // 获得sheet
            Sheet sheet = workbook.getSheetAt(sheetNum - 1);
            // 获得最大行
            int lastRowNum = sheet.getLastRowNum();
            // 获得最大列
            Row firstRow = sheet.getRow(0);
            int lastCellNum = firstRow.getLastCellNum();
            params = new Object[lastRowNum][];
            // 循环遍历，得到列
            for (int i = 1; i <= lastRowNum; i++) {
                // 得到列的内容
                Row row = sheet.getRow(i);
                Object[] arrayListParams = new Object[lastCellNum];
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    arrayListParams[j] = cellValue;

                }
                params[i - 1] = arrayListParams;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return params;
    }

    public static void main(String[] args) {
        Object[][] allParams = readExcel("/api_v4.xlsx", 2);
        for (Object[] param : allParams) {
            for (Object myParams : param) {
                System.out.println(myParams);
            }
        }
    }
}
