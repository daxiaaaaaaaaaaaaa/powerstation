package com.jilian.powerstation.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UpdatePwdVo extends UserInfoVo{
   private String rawAccountPwd	;//True	String		原账号密码(md5加密后的密码)
   private String nowAccountPwd;//	True	String		新账号密码(md5加密后的密码)
   private String confirmPwd;//	true	string		确认密码(md5加密后的密码)
}
