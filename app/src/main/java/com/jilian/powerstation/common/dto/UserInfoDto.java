package com.jilian.powerstation.common.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoDto implements Serializable {
    private String user_cname;//	True	Number		用户名
    private String user_email;//	True	String		邮箱
    private String user_phone;//	True	Number		电话号码
    private String user_status;//	True	String		用户状态(0:停用，1:正常使用)
    private String address;//	True	String		地址
    private String photopath;//	True	Number		头像路径
}
