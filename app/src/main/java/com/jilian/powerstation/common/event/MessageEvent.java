package com.jilian.powerstation.common.event;

import java.io.Serializable;

public class MessageEvent implements Serializable {
    private AlarmMsg alarmMsg;
    private UpdateUserMessage userMessage;

    public UpdateUserMessage getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(UpdateUserMessage userMessage) {
        this.userMessage = userMessage;
    }

    public AlarmMsg getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(AlarmMsg alarmMsg) {
        this.alarmMsg = alarmMsg;
    }
}
