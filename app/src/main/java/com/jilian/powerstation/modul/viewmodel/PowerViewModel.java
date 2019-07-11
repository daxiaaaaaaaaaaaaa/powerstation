package com.jilian.powerstation.modul.viewmodel;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseViewModel;
import com.jilian.powerstation.common.dto.BaseResultDto;
import com.jilian.powerstation.common.dto.PowerCardDto;
import com.jilian.powerstation.common.vo.PowerInfoVo;
import com.jilian.powerstation.factory.Factoty;
import com.jilian.powerstation.modul.repository.PowerRepository;
import com.jilian.powerstation.modul.repository.UserRepository;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by cxz on 2019/6/18
 * <p> 电站的相关
 * Discrebe:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PowerViewModel extends BaseViewModel {
    private static final String TAG = "PowerViewModel";

    /**
     * 电站名片
     */
    private LiveData<BaseDto<PowerCardDto>> powerCardliveData;
    /**
     * 更新固件
     */
    private LiveData<BaseDto<BaseResultDto>> updateliveData;

    public LiveData<BaseDto<BaseResultDto>> getUpdateliveData() {
        return updateliveData;
    }

    public static String getTAG() {
        return TAG;
    }

    public LiveData<BaseDto<PowerCardDto>> getPowerCardliveData() {
        return powerCardliveData;
    }

    /**
     * 电站名片
     * @param powerSn
     */

    public void getPowerCard(String powerSn) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        powerCardliveData = Factoty.getRepository(PowerRepository.class).getPowerCard(vo);
    }

    /**
     * 更新固件
     * @param powerSn
     */
    public void updatePowerFirmwareInfo(String powerSn) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        updateliveData = Factoty.getRepository(PowerRepository.class).updatePowerFirmwareInfo(vo);
    }


}
