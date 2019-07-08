package com.jilian.powerstation.modul.viewmodel;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseViewModel;
import com.jilian.powerstation.common.dto.LoginDto;
import com.jilian.powerstation.common.dto.ReportListDto;
import com.jilian.powerstation.common.vo.ReportVo;
import com.jilian.powerstation.factory.Factoty;
import com.jilian.powerstation.modul.repository.ReportRepository;
import com.jilian.powerstation.modul.repository.UserRepository;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/13
 * <p>
 * Discrebe:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportViewModel extends BaseViewModel {
    private static final String TAG = "ReportViewModel";
    /**
     * 报告数据
     */
    private LiveData<BaseDto<ReportListDto>> reportData;

    public void addReportData(String powerSn,String startTime,String endTime,int type ){
        ReportVo vo = new ReportVo();
        vo.setPowerSn(powerSn);
        vo.setStartTime(startTime);
        vo.setEndTime(endTime);
        vo.setType(type);
        reportData = Factoty.getRepository(ReportRepository.class).getReportData(vo);
    }

}
