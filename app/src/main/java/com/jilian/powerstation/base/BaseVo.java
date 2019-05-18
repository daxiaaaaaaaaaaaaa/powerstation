package com.jilian.powerstation.base;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseVo implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
}
