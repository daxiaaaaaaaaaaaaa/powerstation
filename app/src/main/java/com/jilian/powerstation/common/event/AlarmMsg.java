package com.jilian.powerstation.common.event;

import java.io.Serializable;

public class AlarmMsg implements Serializable {
    private int code;//警告信息

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
