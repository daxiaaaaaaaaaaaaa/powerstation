package com.jilian.powerstation.common.event;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddPowersEvent implements Serializable{
    private int code;

}
