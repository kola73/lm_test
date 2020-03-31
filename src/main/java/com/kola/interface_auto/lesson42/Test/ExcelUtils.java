package com.kola.interface_auto.lesson42.Test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

public class ExcelUtils {
    public static Object[][] readExcel(String path) {
        Object[][] datas = null;
        try {
            InputStream is = ExcelUtils.class.getResourceAsStream(path);
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            datas = new Object[lastRowNum][];
            for (int i = 0; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                Object[] cellValueArray = new Object[lastCellNum];
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    cellValueArray[j] = cellValue;
                }
                datas[i] = cellValueArray;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static void main(String[] args) {
        Object[][] data = readExcel("/api.xlsx");
        for (Object[] datas : data) {
            for (Object params : datas) {
                System.out.println(params + " ");
            }
        }
    }
}
