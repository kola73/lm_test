package com.kola.interface_auto.lesson42;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

/*读取excel
注意：每次修改完excel记得要刷新！！否则无法读取到最新内容
 */

public class ExcelUtils_v2 {
    public static Object[][] readExcel(String excelPath) {
        Object[][] datas = null;
        try {
            InputStream is = ExcelUtils_v2.class.getResourceAsStream(excelPath);
            // 创建工作簿对象
            Workbook workbook = WorkbookFactory.create(is);
            // 获得sheet
            Sheet sheet = workbook.getSheetAt(0);
            // 获得最大行号
            int lastRowNum = sheet.getLastRowNum();
            // 创建一个二维数组，保存行列的数据
            datas = new Object[lastRowNum + 1][];
            for (int i = 0; i <= lastRowNum; i++) {
                // 获得每一行
                Row row = sheet.getRow(i);
                // 获得最大列
                int lastCellNum = row.getLastCellNum();
                // 创建一个一维数组，保存每一列的数据
                Object[] cellValueArray = new Object[lastCellNum];
                for (int j = 0; j < lastCellNum; j++) {
                    // 获得每一列（设置格式，防止报空指针）
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    // 设置类型为String
                    cell.setCellType(CellType.STRING);
                    // 获得每一列的值
                    String cellValue = cell.getStringCellValue();
                    // 把当前列的数据添加到cellValueArray
                    cellValueArray[j] = cellValue;
                }
                // 每遍历一行，要把列的数据添加到二维数组
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
        Object[][] datas = readExcel("/api.xlsx");
        for (Object[] cellValue : datas) {
            for (Object cellValues : cellValue) {
                System.out.print(cellValues + " ");
            }
            System.out.println();
        }
    }
}
