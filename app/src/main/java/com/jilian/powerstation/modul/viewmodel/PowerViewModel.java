package com.jilian.powerstation.modul.viewmodel;

import android.arch.lifecycle.LiveData;

import com.jilian.powerstation.base.BaseDto;
import com.jilian.powerstation.base.BaseViewModel;
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
    private static final String TAG = "UserViewModel";

    /**
     * 注册
     */
    private LiveData<BaseDto<PowerCardDto>> powerCardliveData;

    /***
     * 添加电站
     * @param powerSn SN 码
     * @param powerName 电站名称
     */
    public void getPowerCard(String powerSn, String powerName) {
        PowerInfoVo vo = new PowerInfoVo();
        vo.setPowerSn(powerSn);
        vo.setPowerName(powerName);
        powerCardliveData = Factoty.getRepository(PowerRepository.class).getPowerCard(vo);
    }
}
