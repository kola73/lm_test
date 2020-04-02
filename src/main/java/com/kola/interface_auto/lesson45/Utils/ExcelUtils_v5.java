package com.kola.interface_auto.lesson45.Utils;

import com.kola.interface_auto.lesson45.pojo.ApiInfo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*读取excel v5
1，把存储类型改为了List<ApiInfo>--List表示整体数据，每行是一个ApiInfo
2，为了避免多个sheet造成的重复代码，所以把对象直接改为Object类型，然后新增clazz参数，

测试用例的设计方式：
1，每个接口对应一个测试用例文档
2，所有的接口测试用例相关数据都放在一个excel里，甚至是一个sheet中（本次用这种）
1），url各不相同，写死在一个sheet的话，重复了--冗余--可维护性低（解决方法：数据库通过多表，关联字段解决冗余）
2），各个接口的参数个数不确定，并且名称各不相同（解决方案：放在json格式文件里面）
 */

public class ExcelUtils_v5 {
    public static List<Object> readExcel(String excelPath, int sheetNum, Class clazz) {
        List<Object> objList = new ArrayList<>();
        try {
            InputStream is = ExcelUtils_v5.class.getResourceAsStream(excelPath);
            // 创建工作簿对象
            Workbook workbook = WorkbookFactory.create(is);
            // 获得sheet（要-1的原因：因为是sheet是0开始，但是我这边规定传参从1开始传）
            Sheet sheet = workbook.getSheetAt(sheetNum - 1);
            // 获得最大行号
            int lastRowNum = sheet.getLastRowNum();
            // 获得第一列，然后获得最大列号（第一行是参数的名称，所以它的长度就是最大列）
            Row firstRow = sheet.getRow(0);
            int lastCellNum = firstRow.getLastCellNum();
            // 创建数组，保存表头的值
            String[] columnName = new String[lastCellNum];
            // 遍历第一行的每一列，获得表头的值（apiid，url等）
            for (int k = 0; k < lastCellNum; k++) {
                // 得到值
                Cell cell = firstRow.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                // 把值存到数组
                columnName[k] = cellValue;
            }
            // 创建一个二维数组，保存行列的数据（i=1：引用前面一行是参数名，所以要从1开始）
            for (int i = 1; i <= lastRowNum; i++) {
                // 通过字节码去创建一个对象（替代原先的实例化类，如ApiInfo类）
                Object obj = clazz.newInstance();
                // 获得每一行
                Row row = sheet.getRow(i);
                for (int j = 0; j < lastCellNum; j++) {
                    // 获得每一列（设置格式，防止报空指针）
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    // 设置类型为String
                    cell.setCellType(CellType.STRING);
                    // 获得每一列的值
                    String cellValue = cell.getStringCellValue();
                    // 通过反射拿到每一列的值（pojo类的值的顺序必须和sheet页的统一，否则会报错）
                    // 获得此列的表头
                    String columnNames = columnName[j];
                    // 设置setter方法名称
                    String setMethod = "set" + columnNames;
                    // 得到setter方法
                    Method method = clazz.getMethod(setMethod, String.class);
                    // 反射调用该方法
                    method.invoke(obj, cellValue);
                }
                // 把每一行的值放入大的容器
                objList.add(obj);
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
        return objList;
    }

    public static void main(String[] args) {
        List<Object> lists = readExcel("/api_v4.xlsx", 1, ApiInfo.class);
        for (Object list : lists) {
            System.out.println(list);
        }
    }
}
