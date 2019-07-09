package com.jilian.powerstation.common.dto;

import java.io.Serializable;
import java.util.List;

public class PcsInfoListDto implements Serializable {
    private List<PcsInfoDto> rows;//逆变器列表

    public List<PcsInfoDto> getRows() {
        return rows;
    }

    public void setRows(List<PcsInfoDto> rows) {
        this.rows = rows;
    }
}
