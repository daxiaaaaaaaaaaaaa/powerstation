package com.jilian.powerstation.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/18
 * <p>
 * Discrebe:
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class PowerCardDto {

    private String versionId; //True	Number		固件版本id
    private String versionCode; //True	String		固件版本号
    private String   versionIntro; //True	String		固件版本描述

}
