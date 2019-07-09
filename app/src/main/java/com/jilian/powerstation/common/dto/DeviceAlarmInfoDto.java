package com.jilian.powerstation.common.dto;

import java.io.Serializable;

public class DeviceAlarmInfoDto implements Serializable {
    private String id;//	false	number		索引
    private String nowFault;//	false	String		当前告警
    private String historyFault;//	false	String		历史告警
    private long time;//	false	long		更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNowFault() {
        return nowFault;
    }

    public void setNowFault(String nowFault) {
        this.nowFault = nowFault;
    }

    public String getHistoryFault() {
        return historyFault;
    }

    public void setHistoryFault(String historyFault) {
        this.historyFault = historyFault;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
