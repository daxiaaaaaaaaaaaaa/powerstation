package com.jilian.powerstation.common.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PowerDto implements Serializable {
   private String  powerId;//	True	String		电站id
   private String  powerSn;//	True	String		电站Sn码
   private String  powerName;//	True	String		电站名称
   private String  toDayQuantity;//	True	String		当天发电量
   private String  totalQuantity;//	True	String		累计发电量
   private String  ratedPower	;//True	String		光伏额定功率
   private String  powerImg	;//True	String		电站图片
   private String  powerAddress	;//True	String		电站地址
}
