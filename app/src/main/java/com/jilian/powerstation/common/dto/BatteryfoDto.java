package com.jilian.powerstation.common.dto;

import java.io.Serializable;

public class BatteryfoDto implements Serializable {

    private String id;//	false	Number		索引
    private String power;//	false	Number		充放电功率（正数放电，负数充电）
    private String soc;//	false	Number		电池功率
    private long time;//	false	long		时间
    private String bcmuPhoto;

    public String getBcmuPhoto() {
        return bcmuPhoto;
    }

    public void setBcmuPhoto(String bcmuPhoto) {
        this.bcmuPhoto = bcmuPhoto;
    }

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
