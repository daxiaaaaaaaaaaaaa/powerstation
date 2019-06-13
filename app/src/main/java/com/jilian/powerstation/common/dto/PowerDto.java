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
}
