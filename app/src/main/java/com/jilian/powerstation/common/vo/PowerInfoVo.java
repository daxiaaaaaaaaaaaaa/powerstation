package com.jilian.powerstation.common.vo;

import com.jilian.powerstation.base.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PowerInfoVo extends BaseVo {
   private String  powerSn;//	True	String		电站Sn码
   private String  powerName;//	true	string		电站名称
   private int type;//统计类型（0：逆变器，1：电池，2：智能设备）
   private String id;//id

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public int getType() {
      return type;
   }

   public void setType(int type) {
      this.type = type;
   }

   public String getPowerSn() {
      return powerSn;
   }

   public void setPowerSn(String powerSn) {
      this.powerSn = powerSn;
   }

   public String getPowerName() {
      return powerName;
   }

   public void setPowerName(String powerName) {
      this.powerName = powerName;
   }
}
