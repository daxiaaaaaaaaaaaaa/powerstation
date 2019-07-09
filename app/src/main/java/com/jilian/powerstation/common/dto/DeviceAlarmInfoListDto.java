package com.jilian.powerstation.common.dto;

import java.util.List;

public class DeviceAlarmInfoListDto extends BaseResultDto {
    private List<DeviceAlarmInfoDto> rows;//

    public List<DeviceAlarmInfoDto> getRows() {
        return rows;
    }

    public void setRows(List<DeviceAlarmInfoDto> rows) {
        this.rows = rows;
    }
}
