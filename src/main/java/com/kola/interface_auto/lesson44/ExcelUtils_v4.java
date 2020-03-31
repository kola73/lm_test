package com.kola.interface_auto.lesson44;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

/*读取excel v4
1，表格新增了一个sheet页，目前有2个，一个是apiInfo，一个是requestData，2个用apiId进行关联
2，传参新增了sheetnum选项，并定义从1开始,传1表示第一个sheet，即索引为0的sheet

测试用例的设计方式：
1，每个接口对应一个测试用例文档
2，所有的接口测试用例相关数据都放在一个excel里，甚至是一个sheet中
1），url各不相同，写死在一个sheet的话，重复了--冗余--可维护性低（解决方法：数据库通过多表，关联字段解决冗余）
2），各个接口的参数个数不确定，并且名称各不相同（解决方案：放在json格式文件里面）
 */

public class ExcelUtils_v4 {
    public static Object[][] readExcel(String excelPath, int sheetNum) {
        Object[][] datas = null;
        try {
            InputStream is = ExcelUtils_v4.class.getResourceAsStream(excelPath);
            // 创建工作簿对象
            Workbook workbook = WorkbookFactory.create(is);
            // 获得sheet（要-1的原因：因为是sheet是0开始，如果我要解析第二个sheet的话，必须要-1才能读取到正确的（2-1=1）
            Sheet sheet = workbook.getSheetAt(sheetNum - 1);
            // 获得最大行号
            int lastRowNum = sheet.getLastRowNum();
            // 获得第一列，然后获得最大列号（第一行是参数的名称，所以它的长度就是最大列）
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
                datas[i - 1] = cellValueArray;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static void main(String[] args) {
        Object[][] datas = readExcel("/api_v4.xlsx", 2);
        for (Object[] cellValue : datas) {
            for (Object cellValues : cellValue) {
                System.out.print(cellValues + " ");
            }
            System.out.println();
        }
    }
}
