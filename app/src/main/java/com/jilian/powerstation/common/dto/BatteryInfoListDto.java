package com.jilian.powerstation.common.dto;

import java.io.Serializable;
import java.util.List;

public class BatteryInfoListDto implements Serializable {
    private List<BatteryfoDto> rows;//电池列表

    public List<BatteryfoDto> getRows() {
        return rows;
    }

    public void setRows(List<BatteryfoDto> rows) {
        this.rows = rows;
    }
}
