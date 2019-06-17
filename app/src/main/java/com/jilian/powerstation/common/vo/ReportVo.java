package com.jilian.powerstation.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/13
 * <p>
 * Discrebe:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportVo {
    private String powerSn;
    private String startTime;
    private String endTime;
    private int type;
}
