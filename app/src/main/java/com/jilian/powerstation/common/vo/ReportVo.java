package com.jilian.powerstation.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/13
 * <p>
 * Discrebe:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportVo {
    private String powerSn;
    private String startTime;
    private String endTime;
    private int type;

    public String getPowerSn() {
        return powerSn;
    }

    public void setPowerSn(String powerSn) {
        this.powerSn = powerSn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
