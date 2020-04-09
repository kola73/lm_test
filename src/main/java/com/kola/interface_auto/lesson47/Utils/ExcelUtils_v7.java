package com.kola.interface_auto.lesson47.Utils;

import com.kola.interface_auto.lesson47.pojo.ApiInfo;
import com.kola.interface_auto.lesson47.pojo.CellData;
import com.kola.interface_auto.lesson47.pojo.ExcelObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*读取excel
v7
优化了写入数据的性能问题
新增了行号字段：因为pojo里2个类都应该具有行号，所以我们把行号单独抽取到父类，然后在读取文件的时候，
我们就可以直接继承父类，里面就有一个setRowNum的方法，
v6
把响应结果写回excel（新增ActualResponse字段）
v5
1，把存储类型改为了List<ApiInfo>--List表示整体数据，每行是一个ApiInfo
2，为了避免多个sheet造成的重复代码，所以把对象直接改为Object类型，然后新增clazz参数，

测试用例的设计方式：
1，每个接口对应一个测试用例文档
2，所有的接口测试用例相关数据都放在一个excel里，甚至是一个sheet中（本次用这种）
1），url各不相同，写死在一个sheet的话，重复了--冗余--可维护性低（解决方法：数据库通过多表，关联字段解决冗余）
2），各个接口的参数个数不确定，并且名称各不相同（解决方案：放在json格式文件里面）
 */

/*
知识点补充：
泛型：
在 Java 泛型中存在通配符的概念:
<? extends T>:上界通配符(Upper Bounds Wildcards）
<? super T>:下界通配符(Lower Bounds Wildcards)
List<? extends C> list1; // list1 的元素的类型只能是 C 和 C 的子类
 */
public class ExcelUtils_v7 {
    // 要写的cell数据池
    private static List<CellData> cellDataToWrite = new ArrayList<>();

    // 添加要回写的cellData数据
    public static void addCellData(CellData cellData) {
        cellDataToWrite.add(cellData);
    }

    // 获得所有要写celldata的数据
    public static List<CellData> getCellDataToWrite() {
        return cellDataToWrite;
    }

    /**
     * @param excelPath excel路径
     * @param sheetNum  sheet号
     * @param clazz     pojo类的字节码对象
     * @return
     */
    public static List<? extends ExcelObject> readExcel(String excelPath, int sheetNum, Class<? extends ExcelObject> clazz) {
        List<ExcelObject> objList = new ArrayList<>();
        try {
            InputStream is = ExcelUtils_v7.class.getResourceAsStream(excelPath);
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
                ExcelObject obj = clazz.newInstance();
                // 设置行号(0开始，所以要+1），用多态的方式，本质是子类进行访问
                obj.setRowNum(i + 1);
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

    /**
     * 把响应结果写回到表格
     * 性能问题：
     * 1，如果要写1000次数据，就要io操作1000次
     * ---》只执行一次：先把所有要写的相关数据先收集起来，所有的测试用例执行完毕之后在写
     * 2，如果caseId对应的行靠后，前面遍历的次数会非常多
     * ---》告诉我一个caseId，就能够拿到对应的行（rowNum）
     */
    // 回写数据升级版:批量回写
    public static void batchWrite(String sourceExcelPath, String targetExcelPath, int sheetNum) {
        InputStream is = null;
        Workbook workbook = null;
        OutputStream os = null;
        try {
            // 因为要传相对路径的，所以之前那个获取输入流的方法用不了了，换成现在这个
            is = ExcelUtils_v7.class.getResourceAsStream(sourceExcelPath);
            workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(sheetNum - 1);
            //获得最大行数
            int lastRowNum = sheet.getLastRowNum();
            // 拿出所有要回写的数据
            List<CellData> cellDataList = ExcelUtils_v7.getCellDataToWrite();
            for (CellData cellData : cellDataList) {
                // 得到行号
                int rowNum = ApiUtils.getCaseIdByRowNum(cellData.getCaseId());
                // 得到对应的行（有一行是表头，所以要-1）
                Row row = sheet.getRow(rowNum - 1);
                // 得到要写数据的列
                Cell cellforWrite = row.getCell(cellData.getCellNum() - 1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cellforWrite.setCellType(CellType.STRING);
                cellforWrite.setCellValue(cellData.getResult());
                os = new FileOutputStream(new File(targetExcelPath));
                // 把文件写入到excel
                workbook.write(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        List<ExcelObject> lists = (List<ExcelObject>) readExcel("/api_v4.xlsx", 1, ApiInfo.class);
        for (Object list : lists) {
            System.out.println(list);
        }
    }
}
