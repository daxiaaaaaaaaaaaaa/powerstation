package com.jilian.powerstation.common.dto;

import java.io.Serializable;

public class PcsInfoDto implements Serializable {

    private String id;//	false	number		索引
    private String ratePower;//	false	number		额定功率
    private String nowChargePower;//	false	number		当前充放电功率（正数：放电，负数：充电）
    private long time;//	false	long		时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRatePower() {
        return ratePower;
    }

    public void setRatePower(String ratePower) {
        this.ratePower = ratePower;
    }

    public String getNowChargePower() {
        return nowChargePower;
    }

    public void setNowChargePower(String nowChargePower) {
        this.nowChargePower = nowChargePower;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
