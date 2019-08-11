package com.jilian.powerstation.common.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/13
 * <p>
 * Discrebe: 报告
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportListDto {
    private List<ReportDto> rows;
    private String production;//	False	number	-1	发电量(统计)
    private String consumption;//	false	Number	-1	用电量
    private String refund;//	false	Number	-1	收益(统计)
    private String offset;//	false	Number	-1	Co2减排(统计)

    public List<ReportDto> getRows() {
        return rows;
    }

    public void setRows(List<ReportDto> rows) {
        this.rows = rows;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
