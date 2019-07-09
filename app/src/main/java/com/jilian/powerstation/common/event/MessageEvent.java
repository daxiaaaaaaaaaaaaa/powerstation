package com.jilian.powerstation.common.event;

import java.io.Serializable;

public class MessageEvent implements Serializable {
    private AlarmMsg alarmMsg;

    public AlarmMsg getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(AlarmMsg alarmMsg) {
        this.alarmMsg = alarmMsg;
    }
}
