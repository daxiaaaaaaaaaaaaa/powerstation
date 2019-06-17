package com.jilian.powerstation.modul.repository;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.api.Api;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.CommonRepository;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.common.vo.ReportVo;
import com.jilian.powerstation.common.vo.UserInfoVo;

/**
 * Created by cxz on 2019/6/13
 * <p>
 * Discrebe:
 */
public class ReportRepository extends CommonRepository {

    /**
     * 报告数据
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<ReportListDto>> getReportData(ReportVo vo) {
        return request(Api.getReportData(vo)).send().get();
    }
}
