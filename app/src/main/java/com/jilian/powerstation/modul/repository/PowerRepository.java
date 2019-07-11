package com.jilian.powerstation.modul.repository;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.api.Api;
import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.CommonRepository;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.PowerCardDto;
import com.jilian.powerstation.common.vo.PowerInfoVo;

/**
 * Created by cxz on 2019/6/13
 * <p>
 * Discrebe:
 */
public class PowerRepository extends CommonRepository {

    /**
     * 电站卡片 固件升级
     *
     * @param vo
     * @return
     */
    public LiveData<BaseDto<PowerCardDto>> getPowerCard(PowerInfoVo vo) {
        return request(Api.getPowerCard(vo)).send().get();
    }

    /**
     * 更新固件
     * @param vo
     * @return
     */
    public LiveData<BaseDto<BaseResultDto>> updatePowerFirmwareInfo(PowerInfoVo vo) {
        return request(Api.updatePowerFirmwareInfo(vo)).send().get();
    }

}
