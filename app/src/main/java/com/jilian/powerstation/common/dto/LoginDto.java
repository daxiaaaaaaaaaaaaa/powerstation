package com.jilian.powerstation.common.dto;



import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginDto implements Serializable {
    private String userId;//": 10,
    private String userPhone;//": null,
    private String validCode;//": null,
    private String oldPassword;//": null,
    private String password;//": null,
    private String userName;//": null,
    private String province;//
    private String userImg;//": "http://35.220.153.195:9010//home/shuigong/data//app/magazine-unlock-06-2.3.1298-_F03DD95EA84A56BA178194A3378FA70B.jpg",
    private String sex;//": null,
    private String monthCommission;//: 4000,
    private String orderTotal;//": 1000,
    private int authStatus;//": 认证状态：1 跳到 开始考试 2 跳到 实名验证 3 跳到正在审核 4 跳到审核成功 5 跳到审核失败
}
