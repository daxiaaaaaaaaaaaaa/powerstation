package com.jilian.powerstation.common.dto;

import java.io.Serializable;

public class BatteryfoDto implements Serializable {

    private String id;//	false	Number		索引
    private String power;//	false	Number		充放电功率（正数放电，负数充电）
    private String soc;//	false	Number		电池功率
    private String time;//	false	long		时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
