package com.jilian.powerstation.common.vo;

import com.jilian.powerstation.base.BaseVo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginVo extends BaseVo {
    private String password;
    private String phone;
    private String location;

}
