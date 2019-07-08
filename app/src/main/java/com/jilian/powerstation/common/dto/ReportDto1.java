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
public class ReportDto1 {
    private int inputPower;    //false	number		输入功率
    private int loadPower; //false	number		负载消耗功率
    private int gridPower;    //false	number		并网功率
    private int cdPower;   //false	number		充放电功率
    private int pvProduction;   //false	Number		光伏发电量
    private int loadProduction;   //False	number		负载耗电量
    private int time;    //false	date		时间（年、月、日）

}
