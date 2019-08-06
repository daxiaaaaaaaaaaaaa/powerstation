package com.jilian.powerstation.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;


public class UpdatePwdVo extends UserInfoVo{
   private String rawAccountPwd	;//True	String		原账号密码(md5加密后的密码)
   private String nowAccountPwd;//	True	String		新账号密码(md5加密后的密码)


   public String getRawAccountPwd() {
      return rawAccountPwd;
   }

   public void setRawAccountPwd(String rawAccountPwd) {
      this.rawAccountPwd = rawAccountPwd;
   }

   public String getNowAccountPwd() {
      return nowAccountPwd;
   }

   public void setNowAccountPwd(String nowAccountPwd) {
      this.nowAccountPwd = nowAccountPwd;
   }


}
