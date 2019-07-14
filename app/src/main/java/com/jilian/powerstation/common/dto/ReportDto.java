package com.jilian.powerstation.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/13
 * <p>
 * Discrebe: 报告
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportDto {
    private String inputPower;    //false	number		输入功率
    private String loadPower; //false	number		负载消耗功率
    private String gridPower;    //false	number		并网功率
    private String cdPower;   //false	number		充放电功率
    private String pvProduction;   //false	Number		光伏发电量
    private String loadProduction;   //False	number		负载耗电量
    private String history_estimated_refund;
    private String history_carbon_offset;
    private long time;
}
