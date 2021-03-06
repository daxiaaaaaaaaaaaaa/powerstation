package com.jilian.powerstation.common.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PowerDto implements Serializable {
    private String productName;//	True	String		电站名称
    private String todayPVproduction;//	True	String		当天发电量
    private String historyPVproduction;//	True	String		累计发电量
    private String rated_power;//	True	String		光伏额定功率
    private String address;//	True	String		电站地址
    private String photopath;//	True	String		电站图片
    private String sn;//sn码

    //电站电量详情
    private String grid_a_active_power;//	false	Number		电网功率
    private String load_a_active_power;    //false	Number		负载功率
    private String pv_output_power;    //false	number		光伏功率
    private String run_power;    //false	number		电池功率
    private String today_pv_production;    //false	number		当天发电量
    private String history_pv_production;    //false	number		总发电量
    private String today_consumption;    //false	number		当天用电量
    private String history_consumption;    //false	number		总用电量
    private String today_own_consumption_rate;    //false	number		当日自发自用率
    private String today_own_consumption;    //false	number		当日自发自用电量
    private String totalEquipment; //false	number	暂无值	智能设备运行数量
    private String history_estimated_refund; //false	number		收益估值
    private String history_carbon_offset;	//false number 总计二氧化碳减排

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTodayPVproduction() {
        return todayPVproduction;
    }

    public void setTodayPVproduction(String todayPVproduction) {
        this.todayPVproduction = todayPVproduction;
    }

    public String getHistoryPVproduction() {
        return historyPVproduction;
    }

    public void setHistoryPVproduction(String historyPVproduction) {
        this.historyPVproduction = historyPVproduction;
    }

    public String getRated_power() {
        return rated_power;
    }

    public void setRated_power(String rated_power) {
        this.rated_power = rated_power;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getGrid_a_active_power() {
        return grid_a_active_power;
    }

    public void setGrid_a_active_power(String grid_a_active_power) {
        this.grid_a_active_power = grid_a_active_power;
    }

    public String getLoad_a_active_power() {
        return load_a_active_power;
    }

    public void setLoad_a_active_power(String load_a_active_power) {
        this.load_a_active_power = load_a_active_power;
    }

    public String getPv_output_power() {
        return pv_output_power;
    }

    public void setPv_output_power(String pv_output_power) {
        this.pv_output_power = pv_output_power;
    }

    public String getRun_power() {
        return run_power;
    }

    public void setRun_power(String run_power) {
        this.run_power = run_power;
    }

    public String getToday_pv_production() {
        return today_pv_production;
    }

    public void setToday_pv_production(String today_pv_production) {
        this.today_pv_production = today_pv_production;
    }

    public String getHistory_pv_production() {
        return history_pv_production;
    }

    public void setHistory_pv_production(String history_pv_production) {
        this.history_pv_production = history_pv_production;
    }

    public String getToday_consumption() {
        return today_consumption;
    }

    public void setToday_consumption(String today_consumption) {
        this.today_consumption = today_consumption;
    }

    public String getHistory_consumption() {
        return history_consumption;
    }

    public void setHistory_consumption(String history_consumption) {
        this.history_consumption = history_consumption;
    }

    public String getToday_own_consumption_rate() {
        return today_own_consumption_rate;
    }

    public void setToday_own_consumption_rate(String today_own_consumption_rate) {
        this.today_own_consumption_rate = today_own_consumption_rate;
    }

    public String getToday_own_consumption() {
        return today_own_consumption;
    }

    public void setToday_own_consumption(String today_own_consumption) {
        this.today_own_consumption = today_own_consumption;
    }

    public String getTotalEquipment() {
        return totalEquipment;
    }

    public void setTotalEquipment(String totalEquipment) {
        this.totalEquipment = totalEquipment;
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
}
