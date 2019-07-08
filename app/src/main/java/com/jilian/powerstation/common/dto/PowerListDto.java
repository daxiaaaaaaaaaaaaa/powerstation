package com.jilian.powerstation.common.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PowerListDto extends BaseResultDto {
    private int total;//总记录数
    private List<PowerDto> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PowerDto> getRows() {
        return rows;
    }

    public void setRows(List<PowerDto> rows) {
        this.rows = rows;
    }
}
