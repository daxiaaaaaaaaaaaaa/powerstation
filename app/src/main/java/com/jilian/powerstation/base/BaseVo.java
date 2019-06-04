package com.jilian.powerstation.base;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseVo implements Serializable {
    private Integer page;//开始页数
    private Integer rows;//每页记录数
}
