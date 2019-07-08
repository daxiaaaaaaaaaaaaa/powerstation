package com.jilian.powerstation.base;



import com.jilian.powerstation.common.event.AddPowersEvent;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息总线实体基类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEventMsg implements Serializable {

    private AddPowersEvent addPowersEvent;

    public AddPowersEvent getAddPowersEvent() {
        return addPowersEvent;
    }

    public void setAddPowersEvent(AddPowersEvent addPowersEvent) {
        this.addPowersEvent = addPowersEvent;
    }
}
