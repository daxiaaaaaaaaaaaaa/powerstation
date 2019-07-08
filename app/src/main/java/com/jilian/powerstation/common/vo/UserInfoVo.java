package com.jilian.powerstation.common.vo;

import com.jilian.powerstation.base.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserInfoVo extends BaseVo {
    private String accountName;//	true	string		账号名
    private String accountEmail;//	true	number		账号邮箱
    private String verCode;//	true	string		邮箱验证码
    private String accountPwd;//	true	string		账号密码

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }
}
