package com.kola.interface_auto.lesson48_49.pojo;

/*
excel所有描述sheet的基类
因为要获取行号的话，在excel工具类里，获取到i就能获取到行号，这里改为继承excelObject类以后，创建这个对象就能设置行号
// 通过字节码去创建一个对象（替代原先的实例化类，如ApiInfo类）
ExcelObject obj = clazz.newInstance();
// 设置行号(0开始，所以要+1），用多态的方式，本质是子类进行访问
obj.setRowNum(i + 1);
 */

public abstract class ExcelObject {
    // 行号
    private int rowNum;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }
}
