package com.jilian.powerstation.base;



import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息总线实体基类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEventMsg implements Serializable {

}
