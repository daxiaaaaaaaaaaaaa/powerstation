package com.jilian.powerstation.common.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/13
 * <p>
 * Discrebe: 报告
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportListDto {
    private List<ReportDto> rows;

}
