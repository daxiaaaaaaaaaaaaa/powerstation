package com.jilian.powerstation.common.dto;

import java.io.Serializable;

public class PcsHistoryDataDto implements Serializable {
    private String inputVoltPv1;//	false	number		PV1输入电压
    private String inputCurrPv1;//	false	number		PV1输入电流
    private String inputPowerPv1;//	false	number		PV1输入功率
    private String inputVoltPv2	;//false	number		PV2输入电压
    private String inputCurrPv2	;//false	number		PV2输入电流
    private String inputPowerPv2;//	false	number		PV2输入功率
    private String inputEneryPv	;//false	number		PV发电量
    private String toGrid;//	false	number		电网馈电量
    private String todayConsumption;//	false	number		电网用电量
    private String loadConsumption;//	false	number		负载用电量
    private String gridVolt;//	false	number		电网电压
    private String gridCurr	;//false	number		电网电流
    private String gridFreq	;//false	number		电网频率
    private String gridPower;//	false	number		电网功率
    private String ratePower;//	false	number		电网功率
    private long time;//	false	Long		时间


    @Override
    public String toString() {
        return "PcsHistoryDataDto{" +
                "inputVoltPv1='" + inputVoltPv1 + '\'' +
                ", inputCurrPv1='" + inputCurrPv1 + '\'' +
                ", inputPowerPv1='" + inputPowerPv1 + '\'' +
                ", inputVoltPv2='" + inputVoltPv2 + '\'' +
                ", inputCurrPv2='" + inputCurrPv2 + '\'' +
                ", inputPowerPv2='" + inputPowerPv2 + '\'' +
                ", inputEneryPv='" + inputEneryPv + '\'' +
                ", toGrid='" + toGrid + '\'' +
                ", todayConsumption='" + todayConsumption + '\'' +
                ", loadConsumption='" + loadConsumption + '\'' +
                ", gridVolt='" + gridVolt + '\'' +
                ", gridCurr='" + gridCurr + '\'' +
                ", gridFreq='" + gridFreq + '\'' +
                ", gridPower='" + gridPower + '\'' +
                ", time=" + time +
                '}';
    }



    public String getInputVoltPv1() {
        return inputVoltPv1;
    }

    public void setInputVoltPv1(String inputVoltPv1) {
        this.inputVoltPv1 = inputVoltPv1;
    }

    public String getInputCurrPv1() {
        return inputCurrPv1;
    }

    public void setInputCurrPv1(String inputCurrPv1) {
        this.inputCurrPv1 = inputCurrPv1;
    }

    public String getInputPowerPv1() {
        return inputPowerPv1;
    }

    public void setInputPowerPv1(String inputPowerPv1) {
        this.inputPowerPv1 = inputPowerPv1;
    }

    public String getInputVoltPv2() {
        return inputVoltPv2;
    }

    public void setInputVoltPv2(String inputVoltPv2) {
        this.inputVoltPv2 = inputVoltPv2;
    }

    public String getInputCurrPv2() {
        return inputCurrPv2;
    }

    public void setInputCurrPv2(String inputCurrPv2) {
        this.inputCurrPv2 = inputCurrPv2;
    }

    public String getInputPowerPv2() {
        return inputPowerPv2;
    }

    public void setInputPowerPv2(String inputPowerPv2) {
        this.inputPowerPv2 = inputPowerPv2;
    }

    public String getInputEneryPv() {
        return inputEneryPv;
    }

    public void setInputEneryPv(String inputEneryPv) {
        this.inputEneryPv = inputEneryPv;
    }

    public String getToGrid() {
        return toGrid;
    }

    public void setToGrid(String toGrid) {
        this.toGrid = toGrid;
    }

    public String getTodayConsumption() {
        return todayConsumption;
    }

    public void setTodayConsumption(String todayConsumption) {
        this.todayConsumption = todayConsumption;
    }

    public String getLoadConsumption() {
        return loadConsumption;
    }

    public void setLoadConsumption(String loadConsumption) {
        this.loadConsumption = loadConsumption;
    }

    public String getGridVolt() {
        return gridVolt;
    }

    public void setGridVolt(String gridVolt) {
        this.gridVolt = gridVolt;
    }

    public String getGridCurr() {
        return gridCurr;
    }

    public void setGridCurr(String gridCurr) {
        this.gridCurr = gridCurr;
    }

    public String getGridFreq() {
        return gridFreq;
    }

    public void setGridFreq(String gridFreq) {
        this.gridFreq = gridFreq;
    }

    public String getGridPower() {
        return gridPower;
    }

    public void setGridPower(String gridPower) {
        this.gridPower = gridPower;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
