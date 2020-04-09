package com.kola.interface_auto.lesson46.Myexercise.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import sun.java2d.opengl.WGLSurfaceData;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public List<Object> readExcel(String excelPath, int sheetNum, Class clazz) {
        List<Object> objectList = new ArrayList<>();
        try {
            InputStream is = ExcelUtils.class.getResourceAsStream(excelPath);
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(sheetNum);
            // 获得最大行号和列号
            int lastRowNum = sheet.getLastRowNum();
            Row firstRow = sheet.getRow(0);
            int lastCellNum = firstRow.getLastCellNum();
            // 创建一维数组保存表头数据
            String[] columnName = new String[lastCellNum];
            for (int k = 0; k < lastCellNum; k++) {
                Cell cell = firstRow.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                // 把值保存到数组
                columnName[k] = cellValue;
            }
            // 循环遍历，得到行列的值
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                // 通过字节码去创建一个对象（替代原先的实例化类，如ApiInfo类）
                Object object = clazz.newInstance();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    // 得到表头的值
                    String columnNames = cellValue;
                    // 反射调用，得到里面的值
                    String setMethod = "set" + columnNames;
                    Method method = clazz.getMethod(setMethod, String.class);
                    method.invoke(objectList, cellValue);
                }
                // 把每一行的值放入大的容器
                objectList.add(object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    public static void writeExcel(String excelPath, int sheetNum, String caseId, int cellNum, String result) {
        InputStream is = null;
        Workbook workbook = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(new File(excelPath));
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(sheetNum);
            Row row = sheet.getRow(1);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                if (cellValue.equals(caseId)) {
                    Cell cell1 = row.getCell(cellNum - 1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell1.setCellType(CellType.STRING);
                    cell1.setCellValue(result);
                }
            }
            os = new FileOutputStream(new File(excelPath));
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
