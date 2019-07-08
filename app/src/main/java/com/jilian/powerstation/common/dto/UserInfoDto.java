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

    public String getUser_cname() {
        return user_cname;
    }

    public void setUser_cname(String user_cname) {
        this.user_cname = user_cname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }
}
