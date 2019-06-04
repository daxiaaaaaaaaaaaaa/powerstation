package com.jilian.powerstation.common.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResultDto implements Serializable {
    private String result;
}
