package com.jilian.powerstation.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/13
 * <p>
 * Discrebe: 报告
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportDto {
    private String inputPower;    //false	number		输入功率
    private String loadPower; //false	number		负载消耗功率
    private String gridPower;    //false	number		并网功率
    private String cdPower;   //false	number		充放电功率
    private String pvProduction;   //false	Number		光伏发电量
    private String loadProduction;   //False	number		负载耗电量
    private String history_estimated_refund;
    private String history_carbon_offset;
    private long time;

    public String getInputPower() {
        return inputPower;
    }

    public void setInputPower(String inputPower) {
        this.inputPower = inputPower;
    }

    public String getLoadPower() {
        return loadPower;
    }

    public void setLoadPower(String loadPower) {
        this.loadPower = loadPower;
    }

    public String getGridPower() {
        return gridPower;
    }

    public void setGridPower(String gridPower) {
        this.gridPower = gridPower;
    }

    public String getCdPower() {
        return cdPower;
    }

    public void setCdPower(String cdPower) {
        this.cdPower = cdPower;
    }

    public String getPvProduction() {
        return pvProduction;
    }

    public void setPvProduction(String pvProduction) {
        this.pvProduction = pvProduction;
    }

    public String getLoadProduction() {
        return loadProduction;
    }

    public void setLoadProduction(String loadProduction) {
        this.loadProduction = loadProduction;
    }

    public String getHistory_estimated_refund() {
        return history_estimated_refund;
    }

    public void setHistory_estimated_refund(String history_estimated_refund) {
        this.history_estimated_refund = history_estimated_refund;
    }

    public String getHistory_carbon_offset() {
        return history_carbon_offset;
    }

    public void setHistory_carbon_offset(String history_carbon_offset) {
        this.history_carbon_offset = history_carbon_offset;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
