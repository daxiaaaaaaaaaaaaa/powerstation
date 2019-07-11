package com.jilian.powerstation.common.vo;

import com.jilian.powerstation.base.BaseVo;

public class HistoryVo extends BaseVo {
    private String  powerSn;//	True	String		电站Sn码
    private String startTime;//开始时间
    private int type;//统计类型（0:pv输入电压，1:PV输入电流，
    // 2:pv输入功率，3:电网输出电压，4:电网输出频率，
    // 5:电网输出电流，6:电网输出功率，7:pv发电量，8:馈电网电量，9:电网用电量，10:负载用电量）----逆变器

   // 统计类型（0:电池电压，1:电池电流，2：温度，3:电池功率，4:电池SOC百分比）----电池

    private String id;//逆变器索引

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
