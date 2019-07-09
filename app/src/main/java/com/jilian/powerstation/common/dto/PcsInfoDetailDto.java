package com.jilian.powerstation.common.dto;

import java.io.Serializable;

public class PcsInfoDetailDto implements Serializable {

    private String inputVoltPv1;//	false	number		PV1输入电压
    private String inputCurrPv1;//	false	number		PV1输入电流
    private String inputPowerPv1;//	false	number		PV1输入功率
    private String inputVoltPv2;//	false	number		PV2输入电压
    private String inputCurrPv2;//	false	number		PV2输入电流
    private String inputPowerPv2;//	false	number		PV2输入功率
    private String inputEneryPv1;//	false	number		PV1发电量
    private String inputEneryPv2;//	false	number		PV2发电量
    private String toGrid	;//false	number		电网馈电量
    private String todayConsumption;//	false	number		电网用电量
    private String loadConsumption;//	false	number		负载用电量
    private String loadOutVolt	;//false	number		EPS输出电压
    private String loadOutCurr;//	false	number		EPS输出电流
    private String loadOutFreq;//	false	number		EPS输出频率
    private String loadOutPower;//	false	number		EPS输出功率
    private String gridVolt;//	false	number		电网电压
    private String gridCurr;//	false	number		电网电流
    private String gridFreq;//	false	number		电网频率
    private String gridPower;//	false	number		电网功率
    private String loadActivePower;//	false	number		负载用电功率

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

    public String getInputEneryPv1() {
        return inputEneryPv1;
    }

    public void setInputEneryPv1(String inputEneryPv1) {
        this.inputEneryPv1 = inputEneryPv1;
    }

    public String getInputEneryPv2() {
        return inputEneryPv2;
    }

    public void setInputEneryPv2(String inputEneryPv2) {
        this.inputEneryPv2 = inputEneryPv2;
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

    public String getLoadOutVolt() {
        return loadOutVolt;
    }

    public void setLoadOutVolt(String loadOutVolt) {
        this.loadOutVolt = loadOutVolt;
    }

    public String getLoadOutCurr() {
        return loadOutCurr;
    }

    public void setLoadOutCurr(String loadOutCurr) {
        this.loadOutCurr = loadOutCurr;
    }

    public String getLoadOutFreq() {
        return loadOutFreq;
    }

    public void setLoadOutFreq(String loadOutFreq) {
        this.loadOutFreq = loadOutFreq;
    }

    public String getLoadOutPower() {
        return loadOutPower;
    }

    public void setLoadOutPower(String loadOutPower) {
        this.loadOutPower = loadOutPower;
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

    public String getLoadActivePower() {
        return loadActivePower;
    }

    public void setLoadActivePower(String loadActivePower) {
        this.loadActivePower = loadActivePower;
    }
}
