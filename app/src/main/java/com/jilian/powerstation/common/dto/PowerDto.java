package com.jilian.powerstation.common.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PowerDto implements Serializable {
    private String productName;//	True	String		电站名称
    private String todayPVproduction;//	True	String		当天发电量
    private String historyPVproduction;//	True	String		累计发电量
    private String rated_power;//	True	String		光伏额定功率
    private String address;//	True	String		电站地址
    private String photopath;//	True	String		电站图片
    private String sn;//sn码

    //电站电量详情
    private String grid_a_active_power;//	false	Number		电网功率
    private String load_a_active_power;    //false	Number		负载功率
    private String pv_output_power;    //false	number		光伏功率
    private String run_power;    //false	number		电池功率
    private String today_pv_production;    //false	number		当天发电量
    private String history_pv_production;    //false	number		总发电量
    private String today_consumption;    //false	number		当天用电量
    private String history_consumption;    //false	number		总用电量
    private String today_own_consumption_rate;    //false	number		当日自发自用率
    private String today_own_consumption;    //false	number		当日自发自用电量
    private String totalEquipment; //false	number	暂无值	智能设备运行数量
    private String history_estimated_refund; //false	number		收益估值
    private String history_carbon_offset;	//false number 总计二氧化碳减排
}
