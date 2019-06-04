package com.jilian.powerstation.common.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PowerListDto extends BaseResultDto {
    private int total;//总记录数
    private List<PowerDto> rows;
}
