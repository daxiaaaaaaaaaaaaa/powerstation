package com.jilian.powerstation.common.dto;

import java.io.Serializable;

public class BatteryDataDto implements Serializable {

    private String volt;//	false	Number		电池电压
    private String current;//	false	Number		电池电流
    private String power;//	false	Number		电池功率
    private String avgTemp;//	false	Number		电池平均温度
    private String maxTemp;//	false	Number		电池最高温度
    private String soc;//	false	Number		电池SOC
    private long time;//	false	long		时间

    public String getVolt() {
        return volt;
    }

    public void setVolt(String volt) {
        this.volt = volt;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(String avgTemp) {
        this.avgTemp = avgTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
