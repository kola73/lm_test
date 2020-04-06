package com.kola.interface_auto.lesson47.pojo;

/**
 * 要写回excel的数据
 *
 * @param caseId   :行
 * @param cellNum: 列
 * @param result:  结果
 */
public class CellData {
    private String caseId;
    private int cellNum;
    private String result;

    public String getCaseId() {
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

    public CellData(String caseId, int cellNum, String result) {
        this.caseId = caseId;
        this.cellNum = cellNum;
        this.result = result;
    }

    public CellData() {
    }

    @Override
    public String toString() {
        return "CellData{" +
                "caseId='" + caseId + '\'' +
                ", cellNum=" + cellNum +
                ", result='" + result + '\'' +
                '}';
    }
}
