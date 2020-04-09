package com.kola.interface_auto.MyTest.utils;

import com.kola.interface_auto.MyTest.pojo.ApiDetail;
import com.kola.interface_auto.MyTest.pojo.CellData;
import com.kola.interface_auto.MyTest.pojo.ExcelObject;
import com.kola.java.lesson34.LoginSuccessTest;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static List<CellData> cellData = new ArrayList<>();

    private static List<CellData> getCellDataToWrite() {
        return cellData;
    }


    public static List<? extends ExcelObject> readExcel(String excelPath, int sheetNum, Class<? extends ExcelObject> clazz) {
        List<ExcelObject> objectList = new ArrayList<>();
        InputStream is = null;
        Workbook workbook = null;
        try {
            is = ExcelUtils.class.getResourceAsStream(excelPath);
            // 创建工作簿对象
            workbook = WorkbookFactory.create(is);
            // 获得sheet
            Sheet sheet = workbook.getSheetAt(sheetNum - 1);
            // 获得最大行号和列号
            int lastRowNum = sheet.getLastRowNum();
            Row firstRow = sheet.getRow(0);
            int lastCellNum = firstRow.getLastCellNum();
            // 存放表头的数据
            String[] columnName = new String[lastCellNum];
            // 循环遍历，获取表头并进行存放
            for (int k = 1; k <= lastCellNum; k++) {
                Cell cell = firstRow.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                columnName[k] = cellValue;
            }
            // 获取行列的值
            for (int i = 1; i <= lastRowNum; i++) {
                // 每一行都是一个对象
                ExcelObject obj = clazz.newInstance();
                Row row = sheet.getRow(i);
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValues = cell.getStringCellValue();
                    String cellNames = columnName[j];
                    String setMethod = "set" + cellNames;
                    Method method = clazz.getMethod(setMethod, String.class);
                    method.invoke(objectList, cellValues);
                }
                objectList.add(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objectList;
    }

    public static void batchWrite(String sourceExcelPath, String targetExcelPath, int sheetNum) {
        InputStream is = null;
        Workbook workbook = null;
        OutputStream os = null;
        try {
            is = ExcelUtils.class.getResourceAsStream(sourceExcelPath);
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(sheetNum - 1);
            int lastRowNum = sheet.getLastRowNum();
            List<CellData> cellDataList = ExcelUtils.getCellDataToWrite();
            for (CellData cellData : cellDataList) {
                int rowNum = ApiUtils.getCaseIdByRowNum(cellData.getCaseId());
                Row row = sheet.getRow(rowNum - 1);
                Cell cell = row.getCell(cellData.getCellNum() - 1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(cellData.getResult());
                os = new FileOutputStream(new File(targetExcelPath));
            }
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
