package com.jilian.powerstation.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ForgetVo  extends UserInfoVo{
    private String newPassword;
}
