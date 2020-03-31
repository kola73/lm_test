package com.kola.interface_auto.lesson43;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

/*读取excel v3
版本新增了表头的字段（为了解决比如传参不一致，导致出现了取的行的长度不一致的问题
注意：每次修改完excel记得要刷新！！否则无法读取到最新内容

测试用例的设计方式：
1，每个接口对应一个测试用例文档
2，所有的接口测试用例相关数据都放在一个excel里，甚至是一个sheet中
1），url各不相同，写死在一个sheet的话，重复了--冗余--可维护性低（解决方法：数据库通过多表，关联字段解决冗余）
2），各个接口的参数个数不确定，并且名称各不相同（解决方案：放在json格式文件里面）
 */

public class ExcelUtils_v3 {
    public static Object[][] readExcel(String excelPath) {
        Object[][] datas = null;
        try {
            InputStream is = ExcelUtils_v3.class.getResourceAsStream(excelPath);
            // 创建工作簿对象
            Workbook workbook = WorkbookFactory.create(is);
            // 获得sheet
            Sheet sheet = workbook.getSheetAt(0);
            // 获得最大行号
            int lastRowNum = sheet.getLastRowNum();
            // 获得第一列，然后获得最大列（第一行是参数的名称，所以它的长度就是最大列）
            Row firstRow = sheet.getRow(0);
            int lastCellNum = firstRow.getLastCellNum();
            // 创建一个二维数组，保存行列的数据（i=1：引用前面一行是参数名，所以要从1开始）
            datas = new Object[lastRowNum][];
            for (int i = 1; i <= lastRowNum; i++) {
                // 获得每一行
                Row row = sheet.getRow(i);
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
                // 每遍历一行，要把列的数据添加到二维数组（因为索引正常是从0开始的，但是上面的i从1开始，所以要-1
                datas[i-1] = cellValueArray;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static void main(String[] args) {
        Object[][] datas = readExcel("/api_v3.xlsx");
        for (Object[] cellValue : datas) {
            for (Object cellValues : cellValue) {
                System.out.print(cellValues + " ");
            }
            System.out.println();
        }
    }
}
