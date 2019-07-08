package com.jilian.powerstation.common.dto;

import java.io.Serializable;

public class ConfigInfoDto implements Serializable {

    private int workingType;//	True	Number		电站工作模式（2:自发自用，3:错峰用电，4:应急电源）
    private String soc;//	false	Number		并网截止soc
    private String rechargePower_One;//	false	Number		充电功率1
    private String dischargePower_One;//	false	Number		放电功率1
    private String rechargePower_Two	;//false	Number		充电功率2
    private String dischargePower_Two;//false	Number		放电功率2
    private String rechargeStartTime_One;//	false	string		充电功率开始时间1
    private String rechargeEndTime_One;//false	string		充电功率结束时间1
    private String dischargeStartTime_One;//	false	string		放电功率开始时间1
    private String dischargeEndTime_One;//	false	string		放电功率结束时间1
    private String rechargeStartTime_Two;//	false	string		充电功率开始时间2
    private String rechargeEndTime_Two;//	false	string		充电功率结束时间2
    private String dischargeStartTime_Two;//	false	string		放电功率开始时间2
    private String dischargeEndTime_Two;//	false	string		放电功率结束时间2

    public int getWorkingType() {
        return workingType;
    }

    public void setWorkingType(int workingType) {
        this.workingType = workingType;
    }

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public String getRechargePower_One() {
        return rechargePower_One;
    }

    public void setRechargePower_One(String rechargePower_One) {
        this.rechargePower_One = rechargePower_One;
    }

    public String getDischargePower_One() {
        return dischargePower_One;
    }

    public void setDischargePower_One(String dischargePower_One) {
        this.dischargePower_One = dischargePower_One;
    }

    public String getRechargePower_Two() {
        return rechargePower_Two;
    }

    public void setRechargePower_Two(String rechargePower_Two) {
        this.rechargePower_Two = rechargePower_Two;
    }

    public String getDischargePower_Two() {
        return dischargePower_Two;
    }

    public void setDischargePower_Two(String dischargePower_Two) {
        this.dischargePower_Two = dischargePower_Two;
    }

    public String getRechargeStartTime_One() {
        return rechargeStartTime_One;
    }

    public void setRechargeStartTime_One(String rechargeStartTime_One) {
        this.rechargeStartTime_One = rechargeStartTime_One;
    }

    public String getRechargeEndTime_One() {
        return rechargeEndTime_One;
    }

    public void setRechargeEndTime_One(String rechargeEndTime_One) {
        this.rechargeEndTime_One = rechargeEndTime_One;
    }

    public String getDischargeStartTime_One() {
        return dischargeStartTime_One;
    }

    public void setDischargeStartTime_One(String dischargeStartTime_One) {
        this.dischargeStartTime_One = dischargeStartTime_One;
    }

    public String getDischargeEndTime_One() {
        return dischargeEndTime_One;
    }

    public void setDischargeEndTime_One(String dischargeEndTime_One) {
        this.dischargeEndTime_One = dischargeEndTime_One;
    }

    public String getRechargeStartTime_Two() {
        return rechargeStartTime_Two;
    }

    public void setRechargeStartTime_Two(String rechargeStartTime_Two) {
        this.rechargeStartTime_Two = rechargeStartTime_Two;
    }

    public String getRechargeEndTime_Two() {
        return rechargeEndTime_Two;
    }

    public void setRechargeEndTime_Two(String rechargeEndTime_Two) {
        this.rechargeEndTime_Two = rechargeEndTime_Two;
    }

    public String getDischargeStartTime_Two() {
        return dischargeStartTime_Two;
    }

    public void setDischargeStartTime_Two(String dischargeStartTime_Two) {
        this.dischargeStartTime_Two = dischargeStartTime_Two;
    }

    public String getDischargeEndTime_Two() {
        return dischargeEndTime_Two;
    }

    public void setDischargeEndTime_Two(String dischargeEndTime_Two) {
        this.dischargeEndTime_Two = dischargeEndTime_Two;
    }
}
