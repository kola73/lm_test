package com.kola.interface_auto.MyTest.pojo;

public class ExcelObject {
    private String rowNum;

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public ExcelObject(String rowNum) {
        this.rowNum = rowNum;
    }

    public ExcelObject() {
    }

    @Override
    public String toString() {
        return "ExcelObject{" +
                "rowNum='" + rowNum + '\'' +
                '}';
    }
}
