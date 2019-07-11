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

    private String address;//	false	string		电站地址
    private String powerName;//	false	String		电站名称
    private String deviceName;//	false	string		设备名称
    private String specification;//	false	string		电站规格
    private String version;//	false	string		固件版本
    private String versionDescribed;//	false	string		新版本描述
    private String quantityCou;//	false	number		累计发电量
    private String remark	;//false	string		电站描述
    private long time;//	false	long		上线时间

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionDescribed() {
        return versionDescribed;
    }

    public void setVersionDescribed(String versionDescribed) {
        this.versionDescribed = versionDescribed;
    }

    public String getQuantityCou() {
        return quantityCou;
    }

    public void setQuantityCou(String quantityCou) {
        this.quantityCou = quantityCou;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
