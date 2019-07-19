package com.jilian.powerstation.common.event;

import java.io.Serializable;

public class UpdateUserMessage implements Serializable {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
