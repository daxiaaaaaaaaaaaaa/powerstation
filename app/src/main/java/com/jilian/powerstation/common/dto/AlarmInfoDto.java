package com.jilian.powerstation.common.dto;

import java.io.Serializable;

public class AlarmInfoDto implements Serializable {

    private int total;//	False	Number	0	总记录数（tota>0,表示有告警）;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
