package com.kola.interface_auto.MyTest.pojo;

public class CellData {
    private String caseId;
    private int cellNum;
    private String result;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CellData{" +
                "caseId='" + caseId + '\'' +
                ", cellNum=" + cellNum +
                ", result='" + result + '\'' +
                '}';
    }

    public CellData(String caseId, int cellNum, String result) {
        this.caseId = caseId;
        this.cellNum = cellNum;
        this.result = result;
    }

    public CellData() {
    }
}
