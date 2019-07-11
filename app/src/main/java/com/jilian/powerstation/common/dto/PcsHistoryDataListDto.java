package com.jilian.powerstation.common.dto;

import java.io.Serializable;
import java.util.List;

public class PcsHistoryDataListDto implements Serializable {
    private List<PcsHistoryDataDto> rows;

    public List<PcsHistoryDataDto> getRows() {
        return rows;
    }

    public void setRows(List<PcsHistoryDataDto> rows) {
        this.rows = rows;
    }
}
