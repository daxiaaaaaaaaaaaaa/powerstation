package com.jilian.powerstation.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 电池 历史数据
 */
public class BatteryDataListDto implements Serializable {
    private List<BatteryDataDto> rows;

    public List<BatteryDataDto> getRows() {
        return rows;
    }

    public void setRows(List<BatteryDataDto> rows) {
        this.rows = rows;
    }
}
