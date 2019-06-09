package com.jilian.powerstation.common.vo;

import com.jilian.powerstation.base.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PowerInfoVo extends BaseVo {
   private String  powerSn;//	True	String		电站Sn码
   private String  powerName;//	true	string		电站名称
}
