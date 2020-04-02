package com.kola.interface_auto.lesson45.test.utils;

import com.kola.interface_auto.lesson45.pojo.ApiInfo;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static List<Object> readExcel(String excelPath, int sheetNum, Class clazz) {
        // 创建容器保存对象
        List<Object> objectList = new ArrayList<>();
        try {
            InputStream inputStream = ExcelUtils.class.getResourceAsStream(excelPath);
            // 创建工作簿对象
            Workbook workbook = WorkbookFactory.create(inputStream);
            // 获得sheet
            Sheet sheet = workbook.getSheetAt(sheetNum - 1);
            // 获得最大行号列号
            int lastRowNum = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            int lastCellNum = row.getLastCellNum();
            // 创建一个一维数组保存表名
            String[] arrayLists = new String[lastCellNum];
            // 获得表名
            for (int k = 0; k < lastCellNum; k++) {
                Cell cell = row.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String value = cell.getStringCellValue();
                arrayLists[k] = value;
            }
            // 循环遍历，得到行列的值
            for (int i = 1; i <= lastRowNum; i++) {
                Object objects = clazz.newInstance();
                Row row1 = sheet.getRow(i);
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row1.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    // 反射得到具体的值
                    String method = arrayLists[j];
                    String setMethod = "set" + method;
                    Method method1 = clazz.getMethod(setMethod, String.class);
                    method1.invoke(objects, value);
                }
                objectList.add(objects);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectList;
    }

    public static void main(String[] args) {
        List<Object> params = readExcel("/api_v4.xlsx", 1, ApiInfo.class);
        for (Object object : params) {
            System.out.println(object);
        }
    }
}
