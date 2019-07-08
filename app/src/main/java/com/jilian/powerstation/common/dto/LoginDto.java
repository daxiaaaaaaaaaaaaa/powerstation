package com.jilian.powerstation.common.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginDto extends BaseResultDto {
    private String token;//登录令牌

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
